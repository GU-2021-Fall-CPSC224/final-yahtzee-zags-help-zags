package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {

    JFrame mainWindow;

    public MainGUI() {
        // Frame
        mainWindow = new JFrame("Yahtzee!");
        mainWindow.setBounds(200, 200, 700, 700);
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.getContentPane().setBackground(Color.black);

<<<<<<< HEAD
<<<<<<< HEAD
        // Image
        ImageIcon yahtzeeIcon = new ImageIcon("./YahtzeeMedia/Misc/pumpkin.jpg");
        mainWindow.setIconImage(yahtzeeIcon.getImage());

        // Start Screen
=======
        ImageIcon yahtzeeIcon = new ImageIcon("./YahtzeeMedia/Misc/pumpkin.jpg");
        mainWindow.setIconImage(yahtzeeIcon.getImage());
=======
>>>>>>> 6353d56165fa20f19bb900dc5443fbfc4db489ed



>>>>>>> 9cd1250de0916ec1f14b27869053037ac82c5c64
        mainWindow.setVisible(true);
        startScreen();
    }

    void startScreen() {
        // Panel
        JPanel myPanel = new JPanel();
        myPanel.setBackground(Color.orange);
        mainWindow.add(myPanel);

        // Image
        ImageIcon pumpkinLogo = new ImageIcon("YahtzeeMedia/Logos/pumpking.jpeg");
        myPanel.add(new JLabel(pumpkinLogo));

        // Button
        JButton playButton = new JButton("Play");
        playButton.setForeground(Color.orange);
        playButton.setBackground(Color.black);
        myPanel.add(playButton);
        Dimension playButtonSize = playButton.getPreferredSize();
        playButton.setBounds(330, 350, playButtonSize.width, playButtonSize.height);



        // Action Listener for Button
        playButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        // TODO: start playing game
                    }
                }
        );

        myPanel.setVisible(true);
    }

}
