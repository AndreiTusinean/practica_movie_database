package client;

import client.presentation.view.LoginUI;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Client {

    private static ConnectionClient connection;

    public static void sendMsgToServer(String msg) {
        try {
            connection.sendMessageToServer(msg);
            System.out.println(msg);
        } catch (IOException e) {
            System.out.println("Cannot send message to server");
        }
    }


    //Accounts
    //name      pass    type
    //steve 	123 	employee
    //dave      12      admin


    public static void main(String[] args) {
        try {
            connection = new ConnectionClient(new Socket("localhost", 3000));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        connection.start();
        try {
            connection.sendMessageToServer("test");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Random rand = new Random();
        int index= rand.nextInt(10);
        System.out.println(index);
        LoginUI l = new LoginUI(index);


    }
}
