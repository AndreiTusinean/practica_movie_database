package server;

import client.presentation.model.*;
import server.business.AdminDAO;
import server.business.EmployeeDAO;
import server.business.LoginDAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Instant;
import java.util.Random;

class ConnectionServer extends Thread implements Observer {
    private final Socket clientSocket;

    public ConnectionServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    ObjectOutputStream output;
    ObjectInputStream input;

    @Override
    public void update(int id, String msg) {
        try {
            output.writeObject("notify," + id + "," + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
            while (clientSocket.isConnected()) {


                boolean clientHasData = clientSocket.getInputStream().available() > 0;
                if (clientHasData) {
                    String msg = (String) input.readObject();
                    System.out.println(Instant.now() + " Got from client: " + msg);

                    String[] str = msg.split(",");
                    Item i = new Item();
                    Movie m = new Movie();
                    ItemRequest ir = new ItemRequest();
                    User u = new User();
                    Bill b = new Bill();
                    switch (str[0]) {
                        case "loginCheck":
                            output.writeObject(LoginDAO.checkLoginDAO(str[1], str[2]));
                            break;

                        case "searchName":
                            m.setName(str[1]);
                            output.writeObject(EmployeeDAO.searchMovieNameDAO(m));
                            break;

                        case "searchYear":
                            m.setYear(Integer.parseInt(str[1].trim()));
                            output.writeObject(EmployeeDAO.searchMovieYearDAO(m));
                            break;

                        case "searchGenre":
                            m.setGenre(str[1]);
                            output.writeObject(EmployeeDAO.searchMovieGenreDAO(m));
                            break;

                        case "searchStatus":
                            m.setStatus(str[1]);
                            output.writeObject(EmployeeDAO.searchMovieStatusDAO(m));
                            break;

                        case "addUser":
                            u.setName(str[1]);
                            u.setPass(str[2]);
                            u.setType(str[3]);
                            output.writeObject(AdminDAO.insertUserDAO(u));
                            break;

                        case "removeUser":
                            u.setId(Integer.parseInt(str[1].trim()));
                            output.writeObject(AdminDAO.deleteUserDAO(u));
                            break;

                        case "updateUser":
                            u.setId(Integer.parseInt(str[1].trim()));
                            u.setName(str[2]);
                            u.setPass(str[3]);
                            u.setType(str[4]);
                            output.writeObject(AdminDAO.updateUserDAO(u));
                            break;

                        case "viewUser":
                            u.setId(Integer.parseInt(str[1].trim()));
                            output.writeObject(AdminDAO.viewUserDAO(u));
                            break;

                        case "addMovie":
                            m.setName(str[1]);
                            m.setYear(Integer.parseInt(str[2].trim()));
                            m.setGenre(str[3]);
                            m.setStatus(str[4]);
                            output.writeObject(AdminDAO.insertMovieDAO(m));
                            break;

                        case "removeMovie":
                            m.setId(Integer.parseInt(str[1].trim()));
                            output.writeObject(AdminDAO.deleteMovieDAO(m));
                            break;

                        case "updateMovie":
                            m.setId(Integer.parseInt(str[1].trim()));
                            m.setName(str[2]);
                            m.setYear(Integer.parseInt(str[3].trim()));
                            m.setGenre(str[4]);
                            m.setStatus(str[5]);
                            output.writeObject(AdminDAO.updateMovieDAO(m));
                            break;

                        case "viewMovie":
                            m.setId(Integer.parseInt(str[1].trim()));
                            output.writeObject(AdminDAO.viewMovieDAO(m));
                            break;

                        case "report":
                            output.writeObject(AdminDAO.makeReportDAO(str[1], str[2], str[3]));
                            break;


                        case "viewRequests":
                            output.writeObject(AdminDAO.viewRequestsDAO());
                            break;

                        case "adminGrantRequest":
                            ir.setId(Integer.parseInt(str[1].trim()));
                            output.writeObject(AdminDAO.grantRequestDAO(ir));
                            break;


                        default:
                            break;
                    }

                    output.writeObject("");
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Instant.now() + " Client disconnected?");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // cleanup
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}