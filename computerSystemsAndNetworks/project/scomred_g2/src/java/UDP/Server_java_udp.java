package UDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * A UDP server that processes messages received from clients, reverses and swaps case of characters,
 * counts lowercase letters, sends processed responses, and does not send an "END" signal when finished.
 *
 * The server:
 * - Receives UDP packets from clients, acknowledging each packet.
 * - Reverses the string and swaps the case of characters in the message.
 * - Counts the number of lowercase letters and sends corresponding "SCOMRED is cool!" messages.
 *
 * The server listens for incoming connections on a user-defined port and handles processing as described.
 *
 * UDP server class adapted from code provided by lmn@isep.ipp.pt.
 * The original code was modified to meet the requirements of this project.
 * @author Group 2
 */
public class Server_java_udp {
    private DatagramSocket udpSocket;
    private int maxPacketSize;

    /**
     * Initializes a UDP server on the specified port with a maximum packet size.
     *
     * @param port          The server's listening port (1024-49151).
     * @param maxPacketSize The maximum allowed packet size (0-512 bytes).
     * @throws SocketException If the socket cannot be opened or bound.
     */
    public Server_java_udp(int port, int maxPacketSize) throws SocketException {
        udpSocket = new DatagramSocket(port);
        this.maxPacketSize = maxPacketSize;
    }

    /**
     * Receives and processes UDP packets from a client.
     *
     * This method manages the reception, processing, and response transmission of a message
     * sent by a client over UDP. It follows these steps:
     *
     * 1. **Receives the message length:** Captures the expected size of the incoming message.
     * 2. **Receives message packets:** Assembles the full message while handling packet order.
     * 3. **Processes the message:** Reverses and swaps case of the characters.
     * 4. **Responds to the client:** Sends the processed message, its length,
     *    a lowercase count, and additional messages.
     *
     * The server uses a timeout mechanism to handle lost packets and retransmissions.
     * If the expected message is not received within the given timeframe, the session terminates.
     *
     * @param serverPort The UDP port on which the server is listening.
     * @throws IOException If an error occurs during packet reception or transmission.
     */
    public void receiveAndProcessPackets(int serverPort) {
        try {
            InetAddress clientAddress;
            int clientPort;

            System.out.println("Server is running in port " + serverPort + " and waiting for connections...");

            int lastReceivedMessageLength = -1; // Track the last received message length

            // Receive the length message
            DatagramPacket lengthPacketReceived = receivePacket();
            String lengthMessage = new String(lengthPacketReceived.getData(), 0, lengthPacketReceived.getLength(), StandardCharsets.UTF_8);
            int messageLength = Integer.parseInt(lengthMessage.trim());
            System.out.println("Expected message length: " + messageLength + " bytes");

            clientAddress = lengthPacketReceived.getAddress();
            clientPort = lengthPacketReceived.getPort();

            // If a new message length is received while still processing the previous one, reset buffers**
            if (lastReceivedMessageLength != -1 && messageLength != lastReceivedMessageLength) {
                System.out.println("New message length received! Resetting previous packets...");
            }
            //lastReceivedMessageLength = messageLength; // Update the new expected length

            // Receive the message packets
            udpSocket.setSoTimeout(500); // Setting timeout for receiving packets
            System.out.println("Waiting for the message packets...");

            StringBuilder messageBuilder = new StringBuilder();
            int expectedSeqNum = 0;
            int receivedBytes = 0;

            try {
                while (receivedBytes < messageLength) {
                    DatagramPacket packet = receivePacket();
                    byte[] data = packet.getData();
                    int seqNum = data[0]; // Sequence number is in the first byte
                    String receivedMessage = new String(data, 1, packet.getLength() - 1, StandardCharsets.UTF_8);

                    if (seqNum == expectedSeqNum) { // If the packet sequence is as expected
                        messageBuilder.append(receivedMessage); // Add the received message part to the StringBuilder
                        receivedBytes += receivedMessage.length(); // Update the total received bytes
                        sendAcknowledgment(seqNum, clientAddress, clientPort); // Send ACK for the current packet
                        expectedSeqNum++; // Increase expected sequence number for the next packet
                    } else {
                        // If the packet is out of order, send ACK for the last valid sequence number
                        System.err.println("Packet out of order, expected seqNum: " + expectedSeqNum + " but got seqNum: " + seqNum);
                        sendAcknowledgment(expectedSeqNum - 1, clientAddress, clientPort);
                    }
                }

                udpSocket.setSoTimeout(0); // Remove the timeout for the next part of the process

                //Validate message length
                String originalMessage = messageBuilder.toString();
                System.out.println("Received original message: " + originalMessage);

                //Process the message (reverse and invert)
                String processedMessage = reverseAndInvert(originalMessage);
                int processedLength = processedMessage.getBytes(StandardCharsets.UTF_8).length;
                System.out.println("Processed message (reversed and inverted): " + processedMessage);

                //Send the length of the processed message
                String processedLengthMessage = String.valueOf(processedLength);
                byte[] lengthData = processedLengthMessage.getBytes(StandardCharsets.UTF_8);
                DatagramPacket lengthPacket = new DatagramPacket(lengthData, lengthData.length, clientAddress, clientPort);
                udpSocket.send(lengthPacket);
                System.out.println("Sent processed message length: " + processedLength);

                //Send the processed message in packets
                sendMessageInPackets(clientAddress, clientPort, processedMessage);

                //Count lowercase characters and send it to the client
                int lowercaseCount = countLowercase(processedMessage);
                sendLowercaseCount(lowercaseCount, clientAddress, clientPort);

                //Send "SCOMRED is cool!" messages
                sendCoolMessages(processedMessage, clientAddress, clientPort);

                //Send the "END" message to signal the end of the communication
                sendEndMessage(clientAddress, clientPort);

            } catch (SocketTimeoutException e) {
                System.err.println("Did not receive valid string from client. Terminating!");
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());  // Handle the size error
            }

        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }

    /**
     * Receives a UDP packet.
     *
     * @return The received {@link DatagramPacket}.
     * @throws IOException If an error occurs during reception.
     */
    public DatagramPacket receivePacket() throws IOException {
        int maxPacketSize = 512; // Define the maximum packet size the server can receive
        byte[] buffer = new byte[maxPacketSize];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        udpSocket.receive(packet);
        int packetSize = packet.getLength();
        if (packetSize > maxPacketSize) {
            throw new IllegalArgumentException("Received packet size exceeds expected size: " + packetSize);
        }
        return packet;
    }

    /**
     * Sends an acknowledgment (ACK) for a received packet.
     *
     * @param seqNum       The sequence number of the acknowledged packet.
     * @param clientAddress The client's IP address.
     * @param clientPort   The client's port.
     * @throws IOException If an error occurs during transmission.
     */
    private void sendAcknowledgment(int seqNum, InetAddress clientAddress, int clientPort) throws IOException {
        String ackMessage = "ACK " + seqNum;
        byte[] ackData = ackMessage.getBytes(StandardCharsets.UTF_8);
        DatagramPacket ackPacket = new DatagramPacket(ackData, ackData.length, clientAddress, clientPort);
        udpSocket.send(ackPacket);
    }

    /**
     * Reverses the input string and inverts the case of each character.
     *
     * This method creates a new string by iterating through the original string in reverse order,
     * and inverting the case of each character. Uppercase letters become lowercase, and lowercase
     * letters become uppercase. Non-alphabetic characters remain unchanged.
     * If the input string is null, a NullPointerException may occur during processing.
     *
     * @param input The input string to process. Must not be null.
     * @return The reversed string with inverted case for each character.
     */
    private String reverseAndInvert(String input) {
        StringBuilder reversed = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                reversed.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                reversed.append(Character.toUpperCase(c));
            } else {
                reversed.append(c);
            }
        }
        return reversed.toString();
    }

    /**
     * Sends the processed message in packets, ensuring correct order and waiting for ACKs.
     *
     * @param clientAddress    The client's IP address.
     * @param clientPort       The client's port.
     * @param processedMessage The message to send.
     * @throws IOException If an error occurs during transmission.
     */
    private void sendMessageInPackets(InetAddress clientAddress, int clientPort, String processedMessage) throws IOException {
        byte[] messageBytes = processedMessage.getBytes(StandardCharsets.UTF_8);
        int maxDataSize = maxPacketSize - 1;
        int totalPackets = (int) Math.ceil((double) messageBytes.length / maxDataSize);

        for (int seqNum = 0; seqNum < totalPackets; seqNum++) {
            int startIdx = seqNum * maxDataSize;
            int endIdx = Math.min(startIdx + maxDataSize, messageBytes.length);

            byte[] packetData = new byte[endIdx - startIdx + 1];
            packetData[0] = (byte) seqNum;  // First byte is the sequence number
            System.arraycopy(messageBytes, startIdx, packetData, 1, endIdx - startIdx);

            DatagramPacket packet = new DatagramPacket(packetData, packetData.length, clientAddress, clientPort);
            udpSocket.send(packet);
            System.out.println("Sent packet " + seqNum);

            // Waiting for ACK before sending the next packet
            if (!waitForAcknowledgment(seqNum)) {
                System.out.println("Error: ACK not received for packet " + seqNum);
                return;
            }
        }
    }

    /**
     * Waits for an acknowledgment (ACK) from the client for a specific packet.
     * Retries up to 3 times if no valid ACK is received. If all attempts fail,
     * the server terminates execution.
     *
     * @param seqNum The sequence number of the packet awaiting acknowledgment.
     * @return {@code true} if the expected ACK is received; otherwise, the server exits.
     * @throws SocketTimeoutException If the ACK is not received within the timeout period.
     * @throws SecurityException If a security manager blocks access to the socket.
     * @throws IOException If an error occurs while receiving the ACK.
     */
    private boolean waitForAcknowledgment(int seqNum) throws IOException {
        int retryCount = 0;
        boolean ackReceived = false;

        while (!ackReceived && retryCount < 3) {
            try {
                byte[] ackBuffer = new byte[512];
                DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
                udpSocket.receive(ackPacket);
                String ackMessage = new String(ackPacket.getData(), 0, ackPacket.getLength(), StandardCharsets.UTF_8).trim();
                if (ackMessage.equals("ACK " + seqNum)) {
                    ackReceived = true;
                    System.out.println("ACK received for packet " + seqNum);
                } else {
                    System.err.println("Invalid ACK received: " + ackMessage + " (expected: ACK " + seqNum + ")");
                }
            } catch (SocketTimeoutException e) {
                retryCount++;
                System.err.println("Result transmission failed. Terminating!");
            }
        }

        if (!ackReceived) {
            System.err.println("Result transmission failed. Terminating!");
            udpSocket.close();
            System.exit(1);
        }

        return ackReceived;
    }

    /**
     * Sends the lowercase letter count to the client as a UDP packet.
     *
     * @param lowercaseCount The number of lowercase letters in the processed message.
     * @param clientAddress  The IP address of the client receiving the count.
     * @param clientPort     The port number of the client.
     * @throws IOException If an error occurs during packet transmission.
     */
    private void sendLowercaseCount(int lowercaseCount, InetAddress clientAddress, int clientPort) throws IOException {
        String countMessage = String.valueOf(lowercaseCount);
        byte[] countData = countMessage.getBytes(StandardCharsets.UTF_8);

        DatagramPacket countPacket = new DatagramPacket(countData, countData.length, clientAddress, clientPort);
        udpSocket.send(countPacket);
        System.out.println("Lowercase letter count sent: " + lowercaseCount);
    }
    /**
     * Counts the number of lowercase letters in the input string.
     *
     * This method iterates through the string, counting how many characters are lowercase letters.
     * If the input string is null, a NullPointerException may occur during processing.
     *
     * @param input The input string to process. Must not be null.
     * @return The number of lowercase letters in the string.
     */
    private int countLowercase(String input) {
        int count = 0;
        for (char c : input.toCharArray()) {
            if (Character.isLowerCase(c)) {
                count++;
            }
        }
        return count;
    }
    /**
     * Sends "SCOMRED is cool!" messages to the client, based on the number of lowercase
     * letters in the processed message. Each message is sent as a separate UDP packet.
     *
     * @param processedMessage The processed message (reversed and case-inverted).
     * @param clientAddress    The client's IP address.
     * @param clientPort       The client's port number.
     * @throws IOException If an error occurs during transmission.
     */
    private void sendCoolMessages(String processedMessage, InetAddress clientAddress, int clientPort) throws IOException {
        int lowercaseCount = countLowercase(processedMessage);
        for (int i = 0; i < lowercaseCount; i++) {
            String coolMessage = "SCOMRED is cool!";
            byte[] coolData = coolMessage.getBytes();
            DatagramPacket coolPacket = new DatagramPacket(coolData, coolData.length, clientAddress, clientPort);
            udpSocket.send(coolPacket);
        }
    }

    /**
     * Sends an "END" message to the client, indicating that communication has finished.
     *
     * @param clientAddress The client's IP address.
     * @param clientPort    The client's port number.
     * @throws IOException If an error occurs during transmission.
     */
    private void sendEndMessage(InetAddress clientAddress, int clientPort) throws IOException {
        String endMessage = "END";
        byte[] endData = endMessage.getBytes(StandardCharsets.UTF_8);
        DatagramPacket endPacket = new DatagramPacket(endData, endData.length, clientAddress, clientPort);
        udpSocket.send(endPacket);
    }


    /**
     * Main method that starts the UDP server.
     *
     * @param args Optional command-line arguments: [serverPort, maxPacketSize].
     * @throws SocketException If an error occurs while initializing the server.
     */
    public static void main(String[] args) {
        int port;
        int maxPacketSize;
        Scanner scanner = new Scanner(System.in);

        if (args.length == 2) {
            port = Integer.parseInt(args[0]);
            maxPacketSize = Integer.parseInt(args[1]);
        } else {
            // verify if is a valid port number
            while (true) {
                System.out.print("Please enter the port number (1024 to 49151): ");
                port = scanner.nextInt();
                if (port >= 1024 && port <= 49151) {
                    break;
                }
                System.out.println("Invalid port number. Terminating!");
            }
            // verify if is a valid max packet size
            while (true) {
                System.out.print("Please enter the maximum packet size (2 to 512): ");
                maxPacketSize = scanner.nextInt();
                if (maxPacketSize >= 2 && maxPacketSize <= 512) {
                    break;
                }
                System.out.println("Invalid packet size! Must be between 2 and 512.");
            }
        }
        scanner.close();
        try {
            Server_java_udp server = new Server_java_udp(port, maxPacketSize);
            while (true) {
                server.receiveAndProcessPackets(port);
            }
        } catch (SocketException e) {
            System.err.println("Could not start server: " + e.getMessage());
        }
    }

}
