package edu.gonzaga;

import javax.swing.*;

public class MainGUI {

    JFrame mainWindow;

    public MainGUI() {
        mainWindow = new JFrame("Yahtzee!");
        mainWindow.setBounds(200, 200, 700, 700);
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainWindow.setVisible(true);
    }

}
