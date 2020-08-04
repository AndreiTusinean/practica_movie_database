package client.presentation.controller;

import client.ClientMessage;
import client.presentation.view.AdminMovieUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMovieController {

    public static void AdminMovieUIActionListeners() {

        AdminMovieUI.create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMovieUI.getInputs();
                ClientMessage.viewToClient("addMovie," + AdminMovieUI.name + "," + AdminMovieUI.year + "," + AdminMovieUI.genre + "," + AdminMovieUI.status);
            }
        });

        AdminMovieUI.update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMovieUI.getInputs();
                ClientMessage.viewToClient("updateMovie," + AdminMovieUI.id + "," + AdminMovieUI.name + "," + AdminMovieUI.year + "," + AdminMovieUI.genre + "," + AdminMovieUI.status);
            }
        });

        AdminMovieUI.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMovieUI.getInputId();
                ClientMessage.viewToClient("removeMovie," + AdminMovieUI.id);
            }
        });

        AdminMovieUI.view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMovieUI.getInputId();
                ClientMessage.viewToClient("viewMovie," + AdminMovieUI.id);
            }
        });

        AdminMovieUI.jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String msg = (String) cb.getSelectedItem();
                AdminMovieUI.setStatus(msg);
            }
        });

        AdminMovieUI.bstatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMovieUI.getInputMovie();
                ClientMessage.viewToClient("changeStatus," + AdminMovieUI.name+","+AdminMovieUI.getStatus());
            }
        });
    }
}
