package gr.uop;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


// DECISIONS MADE: for the keyboard entry I decided to make it as simple as possible for anyone
// so for choose an operation a menu is display and the user can choose the operation they want
// to perform. In that way is also easy to ask for a number if the operation needs the second
// number or just send the operation symbol if it is a = sign.
public class CalcClient {
    public static void main(String[] args) {
        try (
            Socket clientSocket = new Socket("localhost", 7777);
            PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner fromServer = new Scanner(clientSocket.getInputStream());
            ) 
        {
            System.out.println("Welcome to the arithmetic calculator");
            System.out.print("First of all write the initial number for the operations: ");
            Scanner keyboard = new Scanner(System.in);
            String initialNum = keyboard.nextLine();

            while (!isANumber(initialNum)){
                System.out.print("You must introduce a number, please try again: ");
                keyboard = new Scanner(System.in);
                initialNum = keyboard.nextLine();
            }
            toServer.println(initialNum); // Send the first number, the server won't response us

            String operation = printMenu(keyboard);
            toServer.println(operation); // Send the operation, the server won't response

            while (!operation.equals("5")){
                System.out.print("Write the next number to calculate the selected operation: ");
                String secondNum = keyboard.nextLine();
                while (!isANumber(secondNum)){
                    System.out.print("You must introduce a number, please try again: ");
                    keyboard = new Scanner(System.in);
                    secondNum = keyboard.nextLine();
                }
                // The second number can be read as a number on the server buffer
                toServer.println(secondNum);
                String result = fromServer.nextLine();
                System.out.println("The result until now is: " + result);

                operation = printMenu(keyboard);
                toServer.println(operation);
            }
            // operation == 5 -> = we go out of the while loop
            String result = fromServer.nextLine();
            System.out.println("The final result is: " + result);
            System.out.println("The operation has finished, closing the communication with the server");
        }
        catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private static String printMenu(Scanner keyboard){
        System.out.println("These are the operation you can compute: ");
        System.out.println("1. +");
        System.out.println("2. -");
        System.out.println("3. *");
        System.out.println("4. /");
        System.out.println("5. = (finish the operation)");
        System.out.print ("Select the number of the operation you want to compute: ");
        String operation = keyboard.nextLine();
        while (!validOperation(operation)){
            System.out.println("The inserted number does not correspond to any operation");
            operation = printMenu(keyboard);
        }
        return operation; // We are sure that the operation is a number so we can cast it
    }

    private static boolean validOperation (String num){
        char[] arrayNum = num.toCharArray();
        if (arrayNum.length > 1){
            return false;
        }
        else{
            for (int i = 0; i<arrayNum.length; i++){
                if (arrayNum[i] != '1' && arrayNum[i] != '2' && arrayNum[i] != '3' && arrayNum[i] != '4' && arrayNum[i] != '5'){
                    // If one of the "digits" is not a number then the introduce line can not be cast to an int
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean isANumber(String num){
        char[] arrayNum = num.toCharArray();
        for (int i = 0; i<arrayNum.length; i++){
            if (arrayNum[i] != '1' && arrayNum[i] != '2' && arrayNum[i] != '3' && arrayNum[i] != '4' && arrayNum[i] != '5' &&
            arrayNum[i] != '6' && arrayNum[i] != '7' && arrayNum[i] != '8' && arrayNum[i] != '9' && arrayNum[i] != '0'){
                // If one of the "digits" is not a number then the introduce line can not be cast to an int
                return false;
            }
        }
        return true;
    }
}
