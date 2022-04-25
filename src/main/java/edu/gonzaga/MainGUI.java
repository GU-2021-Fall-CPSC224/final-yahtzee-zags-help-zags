package edu.gonzaga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainGUI {

    JFrame mainWindow;
    public int result;

    ArrayList<PlayerScoreStatus> playersArray;

    public MainGUI() {
        // Frame
        mainWindow = new JFrame("Yahtzee!");
        mainWindow.setBounds(200, 200, 700, 700);
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.getContentPane().setBackground(Color.black);

        // Image
        ImageIcon yahtzeeIcon = new ImageIcon("./YahtzeeMedia/Misc/pumpkin.jpg");
        mainWindow.setIconImage(yahtzeeIcon.getImage());

        // Start Screen
        mainWindow.setVisible(true);
        startScreen();
    }

    void startScreen() {
        // Panel
        JPanel myPanel = new JPanel();
        myPanel.setBackground(Color.orange);
        mainWindow.add(myPanel);


        // Image
        //ImageIcon pumpkinLogo = new ImageIcon("./YahtzeeMedia/Logos/pumpking.jpeg");
        //JLabel myLabel = new JLabel();
        //myLabel.setIcon(pumpkinLogo);
        //myPanel.add(myLabel);
        //myPanel.add(new JLabel(pumpkinLogo));


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
                        myPanel.setVisible(false);
                        showRules();
                    }
                }
        );

        myPanel.setVisible(true);
    }

    void showRules() {
        JPanel myPanel = new JPanel();
        mainWindow.add(myPanel);

        JButton confirmEntry = new JButton("Play Game!");
        myPanel.add(confirmEntry);

        confirmEntry.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        myPanel.setVisible(false);
                        selectPlayerAmt();
                    }
                }
        );
    }

    void selectPlayerAmt() {
        JPanel myPanel = new JPanel();
        mainWindow.add(myPanel);

        JComboBox selectNumPlayers = new JComboBox();
        for(int i = 1; i <= 4; i++) {
            selectNumPlayers.addItem(i);
        }
        myPanel.add(selectNumPlayers);

        JButton confirmEntry = new JButton("Enter");
        myPanel.add(confirmEntry);


        // Action Listener for Button
        confirmEntry.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        result = Integer.parseInt((String) selectNumPlayers.getSelectedItem());
                    }
                }
        );
    }

    void createScorecards() {
        for (int i = 1; i <= result; i++){
            //PlayerScoreStatus - make new player
            //add player to player array list
        }
    }

}
