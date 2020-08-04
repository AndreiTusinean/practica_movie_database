package client.presentation.controller;

import client.ClientMessage;
import client.presentation.model.User;
import client.presentation.view.AdminMovieUI;
import client.presentation.view.AdminUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AdminController {

    public static void AdminUIActionListeners() {

        AdminUI.jc1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                AdminUI.rep = e.getStateChange() == ItemEvent.SELECTED ? "pdf" : "txt";
                if (AdminUI.rep.equals("pdf"))
                    AdminUI.jc2.setSelected(false);
                else
                    AdminUI.jc2.setSelected(true);
            }

        });

        AdminUI.jc2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                AdminUI.rep = e.getStateChange() == ItemEvent.SELECTED ? "txt" : "pdf";
                if (AdminUI.rep.equals("txt"))
                    AdminUI.jc1.setSelected(false);
                else
                    AdminUI.jc1.setSelected(true);
            }
        });

        AdminUI.create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUI.getInputs();
                User u = new User(AdminUI.name, AdminUI.pass, AdminUI.type);
                ClientMessage.viewToClient("addUser," + u.getName() + "," + u.getPass() + "," + u.getType());
            }
        });

        AdminUI.update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUI.getInputs();
                User u = new User(AdminUI.name, AdminUI.pass, AdminUI.type);
                u.setId(AdminUI.id);
                ClientMessage.viewToClient("updateUser," + u.getId() + "," + u.getName() + "," + u.getPass() + "," + u.getType());
            }
        });

        AdminUI.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUI.getInputId();
                User u = new User();
                u.setId(AdminUI.id);
                ClientMessage.viewToClient("removeUser," + u.getId());
            }
        });

        AdminUI.view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUI.getInputId();
                User u = new User();
                u.setId(AdminUI.id);
                ClientMessage.viewToClient("viewUser," + u.getId());
            }
        });

        AdminUI.report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUI.getInputReport();
                ClientMessage.viewToClient("report," + AdminUI.movie + "," + AdminUI.remarks + "," + AdminUI.rep);
            }
        });

    }
}
