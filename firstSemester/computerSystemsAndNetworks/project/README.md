
# scomred2425_g2 Project - Client-Server Application

This project was developed as part of the SCOMRED course within the SWiTCH DEV programme.

In this team assignment, we enhanced our network programming skills by developing a client-server system in Java.

The system consists of a Reliable User Datagram Protocol (UDP) and a Transport Control Protocol (TCP) application.


# Features:

- **String Reversal**:

Reverses the order of characters in the string and inverts their capitalization.

- **Lowercase Letter Count**:

After reversing and modifying the capitalization, the server counts the number of lowercase characters in the modified string.

- **Additional Response**:

Based on the number of lowercase characters, the server generates an additional response. The phrase "SCOMRED is cool!" is repeated as many times as the number of lowercase characters in the modified string.


# Transport Protocols:


This project uses two transport layer protocols to ensure data transmission between the client and server.

**TCP Protocol**:

- Ensures reliable, ordered, and error-checked delivery of data.

- When a string is sent, TCP guarantees that the data reaches the destination correctly, in the right order, and without any loss.

- It establishes a connection between the client and server, providing mechanisms for error detection and retransmission when necessary.

- This protocol works only between two endpoints.

**Reliable UDP Protocol**:

- Provides a faster but less reliable method for sending data.

- It sends the string without establishing a connection and does not guarantee that the data will arrive in the correct order or at all.

- No error checks or retransmission mechanisms are in place, making it suitable for applications where speed is prioritized over reliability.

- In this project, we implement the stop-and-wait protocol to ensure reliability over UDP.

- This protocol supports data transmission to multiple endpoints.


# Client-Server TCP in Java


This project implements a simple TCP client that connects to a server using a specified hostname and port.

The client is responsible for connecting to the server, data transmission, and error management.

The client sends a string to the server, which then applies the transformations described in the Features section above and sends the modified string back to the client.


-**Detailed steps**:

1. TCP server configuration:

The server is implemented in the Server_java_tcp class and listens on a specified port for incoming client connections.

The server waits for client requests and processes them accordingly.


2. Server wait for a client connection:

The accept () method blocks the server's execution until a client connects.

Once a client connects, the server establishes a communication channel for data exchange.


3. TCP Client configuration:

The client is implemented in the Client_java_tcp class.

It attempts to establish a connection to the server using the provided hostname and port.

If the server cannot be found or if an I/O error occurs during socket creation, the client terminates and display an error message.


4. Data Transmission:

The sendData () method in the Client_java_tcp class is responsible for sending data from the client to the server.

This method sets up communication streams for sending and receiving data, then prints the server's response to the console.

The client can send as many strings as it wants to the server.

Communication only terminates when the client sends the keyword 'exit'.

Once "exit" is sent, the connection is closed on both the client and server sides.

If any I/O errors occur during communication, the programme will terminate and an error will be displayed.


5. Data Modification:

Upon receiving the string, the server applies the transformations as outlined in the Features section above.


6. Response Transmission:

After processing the string, the server sends the modified string back to the client.

Additionally, the server appends the phrase "SCOMRED is cool!" a number of times equal to the number of lowercase characters in the modified string.

The final response is then sent to the client.


# How to use it with the terminal:

1. Start the server with the command:

```bash
java -cp build TCP.Server_java_tcp
   
```
(Note: The server port number can be changed for anyone between 1024 and 4915.)

Alternatively, you can specify the port number directly in the command:

```bash
java -cp build TCP.Server_java_tcp 3300
   
```

2. If successful, the message "Server is running on port 3300 and waiting for connections..." will appear;

3. Start the client with the command:

```bash
java -cp build TCP.Client_java_tcp
```

4. The client will prompt: "Enter server name or IP address:"

5. The client will then prompt: "Enter port:"
   (Note: Admit that you already know the server port number);

6. If everything goes as expected, the prompt ("Type your messages (type 'exit' to quit:")) will appear.
   You can now send multiple strings;

7. To quit, simply type 'exit'.


# Client-Server UDP in Java


This project implements a simple UDP client-server protocol with stop-and-wait principle to add some reliability to UDP protocol.

1. Server Initialization:

The server creates a DatagramSocket on a specific port and must also configure the maximum packet size.

Then, the server starts waiting for client connections.

2. Client Initialization:

The client creates a DatagramSocket, assuming that the server details (IP, port, and maximum packet size) are already known.

3. Client Sends a length message:

First, the client sends a length message to the server.

4. Server Waits For Data:

The server will wait up to 500 milliseconds to receive the string from the client.

5. Client Sends Data:

Then, the client splits the message into smaller packets, numbers them sequentially and sends them to the server.

If the message is broken into multiple segments, each segment must be acknowledged (ACK) individually before sending the next one.

6. Waiting for Server Acknowledgment (ACK):

After the client sends each packet, the server will verify whether the number of bytes in the length message matches the total bytes of the string sent by the client.

If it is true, the client waits for an ACK from the server. If it is not received within the timeout (1000 ms), the client resends the packet up to three times before giving up.

7. Server Receives Data:

The server receives the packets, verifies the order and reconstructs the original message.

8. Data Modification:

Upon receiving the string, the server applies the transformations as outlined in the Features section above.

9. Response Transmission:

After processing the string, the server sends a length message with the number of bytes of the modified String.

Then, the server sends the modified string back to the client.

If the number of bytes are equal in both messages, the client must send an "ACK" to the server.

10. Waiting for Client Acknowledgment (ACK):

The server waits for an ACK from the client. If it is not received within the timeout (1000 ms), the server resends the packet up to three times before giving up.

<div align="center">
<img alt="StopAndWaitUDP.png" src="/scomred_g2/src/images/StopAndWaitUDP.png" width="20%"/>
</div>

# How to use it with the terminal:

1. Start the server with the command:

```bash
java -cp build UDP.Server_java_udp
   
```
(Note: The server port number can be set to any value between 1024 and 4915.)

Alternatively, you can specify the port number directly in the command:

```bash
java -cp build UDP.Server_java_udp 3300 64
   
```

2. Then, you must enter the maximum packet size.

(Note: You must enter a number between 0 and 512 bytes);

3. If successful, the message "Server is running on port 3300 and waiting for connections..." will appear;

4. Start the client with the command:

```bash
java -cp build UDP.Client_java_udp
```

5. The client will prompt: "Enter server name or IP address:"

6. The client will then prompt: "Enter port:"

(Note: Admit that you already know the server port number);

7. The client will then prompt: "Please enter the maximum packet size:".

(Note: You must enter a number between 0 and 512 bytes);

8. If everything goes as expected, the prompt ("Type your messages (type 'exit' to quit:")) will appear. You can now send multiple strings;

9. To quit, simply type 'exit'.



# Challenges


The main challenge in the UDP project was ensuring reliability despite the protocol's inherent unreliability.

To overcome this, a mechanism was implemented where each data segment had to be acknowledged before transmitting the next one.

This approach ensures that the implemented features operate efficiently and reliably.


# References:


1. lmn@isep.ipp.pt - 06/01/2025 - "TCPEchoServer.java" (N/A) Type: source code

2. lmn@isep.ipp.pt - 06/01/2025 - "TCPEchoClient.java" (N/A) Type: source code

3. lmn@isep.ipp.pt - 23/01/2025 - "UDPEchoServer.java" (N/A) Type: source code

4. lmn@isep.ipp.pt - 23/01/2025 - "UDPEchoClient.java" (N/A) Type: source code

5. Oracle (2025) "Class ServerSocket" [Official Documentation]. Java Platform Standard Edition 8 Documentation.

6. Oracle (2025) "Class InputStreamReader" [Official Documentation]. Java Platform Standard Edition 8 Documentation.

7. Oracle (2025) "Class DatagramSocket" [Official Documentation]. Java Platform Standard Edition 8 Documentation.

8. Oracle (2025) "Class DatagramPacket" [Official Documentation]. Java Platform Standard Edition 8 Documentation.

9. Oracle (2025) "Class SocketTimeoutException" [Official Documentation]. Java Platform Standard Edition 8 Documentation.

10. Moreira, A. (2017). Network Programming - UDP. Instituto Superior de Engenharia do Porto (ISEP), Departamento de Engenharia Informática (DEI). Retrieved from lecture materials.


# Contributions:


This project was developed by:

Ana Catarina Magalhães Conde (1241897);

Cláudio Alexandre Ferraz Cunha (1241901);

Fernando Júlio Araújo Moreira (1241905);

Inês Isabel Leal Barbosa (1241909);

João Pedro Gomes de Lemos Pinheiro (1241913);

Mélanie Audrey Gomes Barbosa (1241917);

Pedro Miguel Alves Soares (1241921);

Sérgio Fernando Pinto Ramos (1241925).








