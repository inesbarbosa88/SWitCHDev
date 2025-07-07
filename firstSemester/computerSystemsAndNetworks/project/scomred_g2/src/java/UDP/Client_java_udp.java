package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * UDP client that communicates with a server using the UDP protocol.
 * <p>
 * This client sends messages to the server, receives processed responses,
 * and manages message acknowledgment and retransmission handling.
 * It operates with a configurable timeout and packet size.
 * </p>
 *
 * <p>Main functionalities:</p>
 * <ul>
 *     <li>Establishes a UDP connection with a specified server.</li>
 *     <li>Sends message length and content in multiple packets.</li>
 *     <li>Handles acknowledgments (ACKs) for reliable transmission.</li>
 *     <li>Receives and processes server responses.</li>
 *     <li>Validates user input for hostname, port, and packet size.</li>
 * </ul>
 *
 * UDP client class adapted from code provided by lmn@isep.ipp.pt.
 * The original code was modified to meet the requirements of this project.
 *
 * @author Group 2
 */

public class Client_java_udp {

    private DatagramSocket udpSocket;

    private int maxPacketSize;

    private static final int TIMEOUT = 500;

    /**
     * Initializes a UDP client with a specified timeout and maximum packet size.
     *
     * @param timeout       The socket timeout in milliseconds.
     * @param maxPacketSize The maximum allowed packet size in bytes.
     * @throws SocketException If an error occurs while creating the socket.
     */
    public Client_java_udp(int timeout, int maxPacketSize) throws SocketException {
        udpSocket = new DatagramSocket();
        udpSocket.setSoTimeout(timeout);
        this.maxPacketSize = maxPacketSize;
    }

    /**
     * Sends a message to a server over UDP, ensuring reliable transmission with retries.
     *
     * This method follows a three-step process:
     * 1. **Transmits the message length** to the server.
     * 2. **Sends the message in UDP packets**, handling acknowledgments and retransmissions.
     * 3. **Receives the processed response**, along with additional messages from the server.
     *
     * If the transmission fails after a maximum number of retries, the client terminates.
     *
     * @param hostname The server's hostname or IP address.
     * @param port The destination port on the server.
     * @param message The message to be sent.
     * @throws IOException If an error occurs during network communication.
     */
    public void sendData(String hostname, int port, String message) {
        if (message == null || message.trim().isEmpty()) {
            System.out.println("Empty strings are not allowed. Please enter a valid message.");
            return;  // Retorna sem enviar a mensagem
        }

        try {
            InetAddress address = InetAddress.getByName(hostname);
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            int messageLength = messageBytes.length;

            int maxRetries = 3;
            boolean sentSuccessfully = false;

            for (int attempt = 0; attempt < maxRetries; attempt++) {

                System.out.println("Attempt " + (attempt + 1) + " to send message...");

                sendLengthPacket(address, port, messageLength);
                boolean success = sendMessagePackets(address, port, messageBytes);

                if (success) {
                    sentSuccessfully = true;
                    break; // All packets were successfully sent, exit the retry loop
                } else {
                    System.err.println("Failed to send message. Restarting transmission...");
                }
            }

            if (!sentSuccessfully) {
                System.err.println("Failed to send string. Terminating!");
                udpSocket.close();
                System.exit(1);
            }

            int processedLength = receiveProcessedLength();
            String processedMessage = receiveProcessedMessage(processedLength);

            System.out.println("Processed message received: " + processedMessage);
            receiveResponses();

        } catch (IOException e) {
            System.err.println("Could not connect to server. Terminating!");
            udpSocket.close();
            System.exit(1);
        }
    }

    /**
     * Sends the length of the message as a UDP packet to the specified address and port.
     *
     * @param address       The recipient's IP address.
     * @param port          The recipient's port number.
     * @param messageLength The length of the message to be sent.
     * @throws IOException If an error occurs during packet transmission.
     */
    private void sendLengthPacket(InetAddress address, int port, int messageLength) throws IOException {
        byte[] lengthData = String.valueOf(messageLength).getBytes(StandardCharsets.UTF_8);
        DatagramPacket lengthPacket = new DatagramPacket(lengthData, lengthData.length, address, port);
        udpSocket.send(lengthPacket);
        System.out.println("Sent message length: " + messageLength);
    }

    /**
     * Transmits a message in UDP packets using a reliable acknowledgment-based mechanism.
     *
     * The message is divided into multiple packets, each tagged with a sequence number.
     * Unacknowledged packets are retransmitted up to a predefined limit to ensure delivery.
     *
     * **Process:**
     * 1. Splits the message into appropriately sized packets.
     * 2. Sends each packet and waits for an acknowledgment.
     * 3. Retries transmission for unacknowledged packets.
     * 4. Returns `true` if all packets are successfully received; otherwise, returns `false`.
     *
     * @param address      The recipient's IP address.
     * @param port         The recipient's port number.
     * @param messageBytes The message to be sent as a byte array.
     * @return `true` if the message is fully transmitted and acknowledged; `false` otherwise.
     * @throws IOException If an error occurs during packet transmission.
     */
    private boolean sendMessagePackets(InetAddress address, int port, byte[] messageBytes) throws IOException {
        int maxDataSize = maxPacketSize - 1;
        int totalPackets = (int) Math.ceil((double) messageBytes.length / maxDataSize);

        udpSocket.setSoTimeout(1000); // 1 second timeout for each packet
        boolean[] receivedAcks = new boolean[totalPackets];

        // Send packets and wait for ACKs
        for (int seqNum = 0; seqNum < totalPackets; seqNum++) {
            if (!receivedAcks[seqNum]) {
                byte[] packetData = new byte[Math.min(maxDataSize, messageBytes.length - seqNum * maxDataSize) + 1];
                packetData[0] = (byte) seqNum;
                System.arraycopy(messageBytes, seqNum * maxDataSize, packetData, 1, packetData.length - 1);

                DatagramPacket packet = new DatagramPacket(packetData, packetData.length, address, port);
                udpSocket.send(packet);
                System.out.println("Sent packet " + seqNum);
            }
        }

        // Check for ACKs for each packet
        boolean allAcksReceived = true;
        for (int seqNum = 0; seqNum < totalPackets; seqNum++) {
            if (!receivedAcks[seqNum] && waitForAcknowledgment(seqNum)) {
                receivedAcks[seqNum] = true;
            } else {
                allAcksReceived = false;
            }
        }

        // If all ACKs are received, the message has been sent successfully
        if (allAcksReceived) {
            System.out.println("Message sent successfully.");
            return true;
        }
        return false;
    }

    /**
     * Waits for an acknowledgment (ACK) from the server for a specific packet.
     *
     * @param seqNum The sequence number of the packet expecting an ACK.
     * @return {@code true} if the expected ACK is received, otherwise {@code false}.
     * @throws IOException If an error occurs while receiving the ACK packet.
     */
    private boolean waitForAcknowledgment(int seqNum) throws IOException {
        byte[] ackBuffer = new byte[512];
        DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);

        try {
            udpSocket.receive(ackPacket);
            String ackMessage = new String(ackPacket.getData(), 0, ackPacket.getLength(), StandardCharsets.UTF_8).trim();
            return ackMessage.equals("ACK " + seqNum);
        } catch (SocketTimeoutException e) {
            System.err.println("Timeout waiting for ACK for " + seqNum);
            return false;
        }
    }

    /**
     * Receives the length of the processed message from the server.
     *
     * @return The length of the processed message.
     * @throws IOException           If an error occurs while receiving the packet.
     * @throws NumberFormatException If the received data cannot be parsed as an integer.
     */
    private int receiveProcessedLength() throws IOException {
        byte[] lengthBuffer = new byte[256];
        DatagramPacket lengthPacket = new DatagramPacket(lengthBuffer, lengthBuffer.length);
        udpSocket.receive(lengthPacket);
        String lengthMessage = new String(lengthPacket.getData(), 0, lengthPacket.getLength(), StandardCharsets.UTF_8).trim();
        return Integer.parseInt(lengthMessage);
    }

    /**
     * Receives the processed message from the server in multiple UDP packets.
     * Each packet is reassembled based on sequence numbers until the full message is received.
     * Acknowledgments (ACKs) are sent for each received packet.
     *
     * @param processedLength The expected length of the processed message.
     * @return The fully reconstructed processed message.
     * @throws IOException If an error occurs during packet reception.
     */
    private String receiveProcessedMessage(int processedLength) throws IOException {
        byte[] messageBuffer = new byte[processedLength];
        int bytesReceived = 0;

        while (bytesReceived < processedLength) {
            byte[] buffer = new byte[maxPacketSize];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            udpSocket.receive(packet);

            int seqNum = buffer[0];
            System.arraycopy(buffer, 1, messageBuffer, bytesReceived, packet.getLength() - 1);
            bytesReceived += packet.getLength() - 1;

            sendAcknowledgment(packet.getAddress(), packet.getPort(), seqNum);
        }

        return new String(messageBuffer, StandardCharsets.UTF_8);
    }

    /**
     * Receives and processes responses from the server.
     * <p>
     * The method listens for incoming UDP packets and processes them based on their content:
     * - The first received message is stored as the reversed message.
     * - The second message is parsed as the lowercase letter count.
     * - Subsequent messages containing "SCOMRED is cool!" are stored.
     * - Reception stops when an "END" message is received.
     * </p>
     *
     * @throws IOException If an error occurs while receiving packets.
     */
    private void receiveResponses() throws IOException {
        String reversedMessage = "";
        int lowercaseCount = 0;
        List<String> scomredMessages = new ArrayList<>();
        boolean receiving = true;

        byte[] receiveData = new byte[512];

        while (receiving) {
            DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
            udpSocket.receive(packet);
            String receivedMessage = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8).trim();

            if (receivedMessage.equals("END")) {
                receiving = false;
            } else if (reversedMessage.isEmpty()) {
                reversedMessage = receivedMessage;
            } else if (lowercaseCount == 0) {
                try {
                    receivedMessage = receivedMessage.trim();
                    lowercaseCount = Integer.parseInt(receivedMessage);
                } catch (NumberFormatException e) {
                    scomredMessages.add(receivedMessage);
                }
            } else if (receivedMessage.startsWith("SCOMRED is cool!")) {
                scomredMessages.add(receivedMessage);
            }
        }

        if (!reversedMessage.isEmpty()) {
            System.out.println("The number of lowercase letters is " + reversedMessage + ".");
        }

        for (String messageCool : scomredMessages) {
            System.out.println(messageCool);
        }
    }

    /**
     * Sends an acknowledgment (ACK) for a received packet.
     *
     * @param address The client's IP address.
     * @param port    The client's port number.
     * @param seqNum  The sequence number of the acknowledged packet.
     * @throws IOException If an error occurs during transmission.
     */
    private void sendAcknowledgment(InetAddress address, int port, int seqNum) throws IOException {
        String ackMessage = "ACK " + seqNum;
        byte[] ackData = ackMessage.getBytes(StandardCharsets.UTF_8);
        DatagramPacket ackPacket = new DatagramPacket(ackData, ackData.length, address, port);
        udpSocket.send(ackPacket);
    }

    /**
     * Closes the UDP socket, releasing any associated resources.
     */
    public void close() {
        udpSocket.close();
    }

    /**
     * Starts the UDP client, prompting the user for server details and message input.
     * <p>
     * The client requests the server's hostname/IP, port, and maximum packet size,
     * validating each input before proceeding. It then allows the user to send
     * messages to the server until "exit" is entered.
     * </p>
     *
     * @param args Command-line arguments (not used).
     * @throws IOException           If an error occurs while reading user input.
     * @throws NumberFormatException If the port or packet size input is invalid.
     */
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Please enter the server name or IP address: ");
            String hostName = reader.readLine().trim();
            if (hostName.isEmpty()) {
                System.err.println("Invalid server address. Terminating!");
                System.exit(1);
            }

            System.out.print("Please enter the port number (1024 to 49151): ");
            int port = Integer.parseInt(reader.readLine().trim());
            if (port < 1024 || port > 49151) {
                System.err.println("Invalid port number. Terminating!");
                System.exit(1);
            }


            System.out.print("Please enter the maximum packet size (2 to 512): ");
            int maxPacketSize = Integer.parseInt(reader.readLine().trim());
            if (maxPacketSize < 2 || maxPacketSize > 512) {
                System.err.println("Invalid maximum packet size. Terminating!");
                System.exit(1);
            }

            Client_java_udp client = new Client_java_udp(TIMEOUT, maxPacketSize);

            while (true) {
                System.out.print("Type your messages (type 'exit' to quit):");
                String message = reader.readLine().trim();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                client.sendData(hostName, port, message);
            }

            client.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Could not fetch result.Terminating!" + e.getMessage());
        }
    }
}
