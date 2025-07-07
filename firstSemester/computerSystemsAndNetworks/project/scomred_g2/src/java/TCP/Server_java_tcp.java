package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * A TCP server that processes strings sent by clients and provides advanced functionalities.
 * It reverses strings, inverts their case, counts lowercase letters, and provides additional responses.
 * <p>
 * This class provides functionalities such as:
 * - Accepting client connections via TCP.
 * - Reversing and inverting the case of input strings.
 * - Counting lowercase letters and repeating messages accordingly.
 * <p>
 * The server allows for user-configured ports or command-line arguments to start it.
 * <p>
 * TCP server class adapted from code provided by lmn@isep.ipp.pt.
 * The original code was modified to meet the requirements of this project.
 *
 * @author Group 2
 */

public class Server_java_tcp {

    private ServerSocket serverSocket;

    /**
     * The main method to start the server.
     * <p>
     * This method initializes the server with a specified port, either provided as a command-line argument
     * or entered by the user during runtime. It validates the port number to ensure it falls within the range
     * of 1024 to 49151. If no valid port is provided, the server will not start. Once a valid port is selected,
     * the server starts and waits for incoming client connections.
     * <p>
     * Any potential `IOException` or `NumberFormatException` that may occur during input handling is caught
     * and managed within the method, with appropriate error messages displayed to the user.
     *
     * @param args Command-line arguments. The port number can be passed as the first argument.
     *             If not provided, the user is prompted to input the port number.
     */
    public static void main(String[] args) {
        int port;
        // Check if the port number is passed as a command-line argument
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
                if (port < 1024 || port > 49151) {
                    System.err.println("Invalid port number. Please use a port number between 1024 and 49151.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Port must be a valid integer.");
                return;
            }
        } else {
            // If no argument is passed, prompt user for input
            System.out.print("Enter the port number to start the server (between 1024 and 49151): ");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    try {
                        String input = reader.readLine();
                        port = Integer.parseInt(input);

                        if (port < 1024 || port > 49151) {
                            System.err.println("Invalid port number. Terminating!");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid input. Please enter a valid port number:");
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading input: " + e.getMessage());
                return;
            }
        }

        Server_java_tcp server = new Server_java_tcp(port);
        server.waitConnections();
    }

    /**
     * Initializes the server and binds it to the specified port.
     * <p>
     * This constructor sets up the `ServerSocket` and binds it to the specified port. If the server
     * fails to bind to the port (e.g., due to the port being in use or insufficient permissions),
     * an error message is printed, and the application terminates.
     * <p>
     * The potential `IOException` that may occur when binding to the specified port is caught and managed
     * internally. If an error occurs, an appropriate error message will be displayed to the user, and the
     * program will terminate.
     *
     * @param port The port number to bind the server to. It must be a number between 1024 and 49151.
     */
    public Server_java_tcp(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running on port " + port + " and waiting for connections...");
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Waits for client connections and handles their requests.
     * This method continuously listens for new client connections. For each client connection:
     * <ul>
     *   <li>It reads input strings sent by the client.</li>
     *   <li>Processes the string by reversing it and inverting the case of its characters.</li>
     *   <li>Counts the number of lowercase letters and sends back appropriate messages.</li>
     * </ul>
     * If the client sends the string "exit", the connection is terminated for that client.
     * The server remains active and continues to listen for other connections.
     * The potential `IOException` that may occur during reading input or writing output is caught and handled
     * within the method. Appropriate error messages are logged if the exception occurs.
     */
    public void waitConnections() {
        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                System.out.println("Connected to " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.equalsIgnoreCase("exit")) {
                        System.out.println("Client disconnected.");
                        break;
                    }
                    if (inputLine.trim().isEmpty()) {
                        out.println("The string is empty. Please send a valid string.");
                        continue;
                    }
                    // Processing the input string
                    System.out.println("Server received: " + inputLine.length() + " bytes.");
                    String reversed = reverseAndInvert(inputLine);
                    int lowercaseCount = countLowercase(reversed);
                    String repeatedMessage = "SCOMRED is cool!\n".repeat(lowercaseCount);
                    // Sending results to the client
                    out.println(reversed);
                    out.println("The number of lowercase letters is " + lowercaseCount + ".");
                    out.println(repeatedMessage);
                    System.out.println("Processed input: " + inputLine);
                    System.out.println("Results sent to client.");
                }

            } catch (IOException e) {
                System.err.println("Server exception: " + e.getMessage());
            }
        }
    }

    /**
     * Reverses the input string and inverts the case of each character.
     * <p>
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
     * Counts the number of lowercase letters in the input string.
     * <p>
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
}


