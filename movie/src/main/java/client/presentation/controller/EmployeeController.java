package client.presentation.controller;

import client.ClientMessage;
import client.presentation.model.Movie;
import client.presentation.view.EmployeeUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {

    public static void EmployeeUIActionListeners(){
        EmployeeUI.bname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI.getInputName();
                ClientMessage.viewToClient("searchName,"+EmployeeUI.name);
            }
        });

        EmployeeUI.byear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI.getInputYear();
                ClientMessage.viewToClient("searchYear,"+EmployeeUI.year);
            }
        });
        EmployeeUI.bgenre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI.getInputGenre();
                ClientMessage.viewToClient("searchGenre,"+EmployeeUI.genre);
            }
        });
        EmployeeUI.bstatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI.getInputStatus();
                ClientMessage.viewToClient("searchStatus,"+EmployeeUI.status);
            }
        });

        EmployeeUI.bfavorite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI.getInputName();
                ClientMessage.viewToClient("addFavorite,"+EmployeeUI.name+","+EmployeeUI.getEmployeeName());
            }
        });

        EmployeeUI.bmail.addActionListener(e -> {
            EmployeeUI.getInputMail();
            ClientMessage.viewToClient("sendMail,"+EmployeeUI.subject+","+EmployeeUI.content);
        });


    }
}
