package client;

import client.presentation.view.AdminMovieUI;
import client.presentation.view.AdminUI;
import client.presentation.view.EmployeeUI;
import client.presentation.view.LoginUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Instant;

public class ConnectionClient extends Thread {
    private final Socket socket;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;


    public ConnectionClient(Socket socket) throws IOException {
        this.socket = socket;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    String userType = "";
    String emplName = "";

    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                // Seems that input.available() is not reliable?
                boolean serverHasData = socket.getInputStream().available() > 0;
                if (serverHasData) {
                    String msg = (String) input.readObject();
                    System.out.println(Instant.now() + " Got from server: " + msg);

                    String[] str = msg.split(",");
                    switch (str[0]) {
                        case "loginCheck":
                            switch (str[1]) {
                                case "employee":
                                    EmployeeUI emp = new EmployeeUI(str[3].trim());
                                    emplName = emp.getEmployeeName();
                                    System.out.println("Employee name is: "+emplName);
                                    LoginUI.showMessage("Employee");
                                    LoginUI.frame.setVisible(false);
                                    userType = "employee";
                                    break;
                                case "admin":
                                    AdminMovieUI ad = new AdminMovieUI();
                                    AdminUI adm = new AdminUI();
                                    LoginUI.frame.setVisible(false);
                                    LoginUI.showMessage("Admin");
                                    userType = "admin";
                                    break;
                                default:
                                    LoginUI.showMessage("Username or password incorrect ");
                            }
                            break;

                        case "searchName":

                        case "searchYear":

                        case "searchGenre":

                        case "addFavorite":

                        case "searchStatus":
                            EmployeeUI.showMessage(str[1]);
                            break;

                        case "addUser":

                        case "removeUser":

                        case "updateUser":

                        case "viewUser":

                        case "addMovie":

                        case "removeMovie":

                        case "updateMovie":

                        case "viewMovie":

                        case "changeStatus":

                        case "report":
                            AdminUI.showMessage(str[1]);
                            break;

                        case "notify":
                            //loc = Integer.parseInt(str[1].trim());
                            //if (loc == manLoc && userType.equals("manager")) {
                            //    ManagerUI.showMessage(str[2]);
                            //    ManagerUI.setT2(str[2]);
                            //}
                            break;


                        default:
                            break;
                    }
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Instant.now() + " Server disconnected");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToServer(String message) throws IOException {
        output.writeObject(message);
    }
}