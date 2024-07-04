package gr.uop;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


// Its a server for one client so we will do a simple server that just allow
// the comunication with one client at the same time
public class CalcServer {
    public static void main(String[] args) {
        try (
            ServerSocket serverSocket = new ServerSocket(7777);
            Socket connectionSocket = serverSocket.accept();
            Scanner fromClient = new Scanner(connectionSocket.getInputStream());
            PrintWriter toClient = new PrintWriter(connectionSocket.getOutputStream(), true)
        ) {
            System.out.println("Client connected to the server");
            // Read the first number send by the client and don't responde
            int result = fromClient.nextInt(); 
            System.out.println("First number receiver = " + result);

            int operation = fromClient.nextInt(); // Read the operation to compute
            System.out.println("Operation received = " + operation);

            while (operation != 5) {
                // Its an arithmetical sign so we read the next entry, the second number
                int number = fromClient.nextInt();
                System.out.println("Second number received = " + number);
                switch (operation) {
                    case 1: // Addition
                        result = result + number;
                        System.out.println("Temporal result = " + result);
                        toClient.println(result);
                        break;

                    case 2: // Subtraction
                        result = result - number;
                        System.out.println("Temporal result = " + result);
                        toClient.println(result);
                        break;

                    case 3: // Multiplication
                        result = result * number;
                        System.out.println("Temporal result = " + result);
                        toClient.println(result);
                        break;

                    case 4: // Division
                        result = result / number;
                        System.out.println("Temporal result = " + result);
                        toClient.println(result);
                        break;

                    default:
                        System.err.println("Error on the operation received");
                        break;
                }

                // We read the next operation sended by the client
                operation = fromClient.nextInt(); // Read the operation to compute
                System.out.println("Operation received = " + operation);
            }

            // The sign received is a = so we just need to return result
            System.out.println("Final result = " + result);
            toClient.println(result);
            System.out.println ("The communication has finished closing the server...");
        }
        catch (IOException e) {
            System.out.println(e);
        }
        // We don't need to close the socket due to the fact that a try-catch structure with resources
        // close the socket when finish the communication.
    }
}
