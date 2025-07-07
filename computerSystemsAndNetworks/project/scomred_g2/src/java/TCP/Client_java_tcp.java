package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * A simple TCP client that connects to a server, sends user input, and receives responses.
 *
 * This client allows the user to specify the server address and port. Then, it establishes a connection,
 * sends messages to the server, and prints responses. The user can terminate the communication by typing
 * "exit".
 *
 * TCP client class adapted from code provided by lmn@isep.ipp.pt.
 * The original code was modified to meet the requirements of this project.
 *
 * @author Group 2
 */
public class Client_java_tcp {

    private Socket socket;

    /**
     * The main method to start the client.
     *
     * This method initializes the client by asking for the server address and port from the user.
     * It then starts the communication process with the server.
     * Any `IOException` that occurs during input handling is caught and managed within the method.
     *
     * @param args Command-line arguments, not used in this case.
     */
    public static void main(String[] args) {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Request server address
            System.out.print("Enter server name or IP address: ");
            String serverAddress = consoleReader.readLine();

            if (serverAddress == null || serverAddress.trim().isEmpty()) {
                System.err.println("Invalid server address. Terminating!");
                System.exit(1);
            }

            // Request port number
            System.out.print("Enter port: ");
            int port= -1;
            try {
                port = Integer.parseInt(consoleReader.readLine());
                if (port < 1024 || port > 49151) {
                    System.err.println("Invalid port number. Terminating!");
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
                System.err.println("Port must be a valid integer. Terminating!");
                System.exit(1);
            }

            // Start the client
            Client_java_tcp client = new Client_java_tcp(serverAddress, port);
            client.sendData();

        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }
    /**
     * Initializes the TCP client with a specified server address and port.
     *
     * This constructor attempts to establish a connection to the server at the provided hostname
     * and port. If the connection fails or the server cannot be found, appropriate error messages
     * are displayed, and the program terminates.
     *
     * The `UnknownHostException` and `IOException` exceptions that may occur during the connection
     * process are caught and managed internally within the method.
     *
     * @param hostname The address (either domain name or IP) of the server to connect to.
     * @param port     The port number on which the server is listening.
     */
    public Client_java_tcp(String hostname, int port) {
        try {
            socket = new Socket(hostname, port);
            System.out.println("Connected to the server at " + hostname + ", port number: " + port);
        } catch (UnknownHostException e) {
            System.err.println("Could not connect to server. Terminating!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Could not fetch result. Terminating!");
            System.exit(1);
        }
    }
    /**
     * Starts communication with the server.
     *
     * This method continuously reads input from the user, sends it to the server, and prints the
     * responses. If the user types "exit", the connection is terminated. It handles I/O errors that
     * might occur during communication, such as issues with reading input or writing output. The method
     * ensures that the socket is closed when the communication ends.
     *
     * Any `IOException` that occurs during communication is caught and handled within the method, and
     * an error message is displayed to the user.
     */
    public void sendData() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Type your messages (type 'exit' to quit):");

            String inputLine;
            while (true) {
                System.out.print("Enter string: ");
                inputLine = userInput.readLine();

                if (inputLine == null || inputLine.trim().isEmpty()) {
                    System.out.println("Empty strings are not allowed. Please enter a valid message.");
                    continue;
                }

                if ("exit".equalsIgnoreCase(inputLine)) {
                    System.out.println("Exiting...");
                    break;
                }

                // Send message to server
                out.println(inputLine);

                // Read and print server responses
                String responseLine;
                while ((responseLine = in.readLine()) != null && !responseLine.isEmpty()) {
                    System.out.println("Server: " + responseLine);
                }
            }

        } catch (IOException e) {
            System.err.println("Could not fetch result. Terminating!" + e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }

}
