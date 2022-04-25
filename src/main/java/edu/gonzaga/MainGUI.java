package edu.gonzaga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainGUI {

    JFrame mainWindow;
    public Integer result;

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
                        result = Integer.parseInt(selectNumPlayers.getSelectedItem().toString());
                        try {
                            createScorecards();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            playerTurn();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        myPanel.setVisible(false);
                    }
                }
        );
    }

    void createScorecards() throws FileNotFoundException {
        for (int i = 1; i <= result; i++){
            PlayerScoreStatus currentPlayerScoreStatus = new PlayerScoreStatus(i);
            currentPlayerScoreStatus.createPlayerScoreFile();
        }
    }

    void playerTurn() throws IOException {
        HandPanel handPanel = new HandPanel(350, 0);
        mainWindow.add(handPanel);

        PlayerScoreStatus scoreStatus = new PlayerScoreStatus(1);

        ScorePanelGUI currentPlayer = new ScorePanelGUI();
        JPanel currentPanel = currentPlayer.showScoreCard(scoreStatus);
        mainWindow.add(currentPanel);

        JButton nextButton = new JButton("Next");
        JPanel newPanel = new JPanel();
        newPanel.add(nextButton);
        nextButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        ArrayList<String> diceStrings = new ArrayList<>();
                        int[] diceArray = handPanel.getHandValues();
                        for (int i = 0; i < 5; i++){
                            diceStrings.add(Integer.toString(diceArray[i]));
                        }

                        try {
                            JPanel updatedPanel = currentPlayer.updateScores(diceStrings, scoreStatus);
                            currentPanel.setVisible(false);
                            mainWindow.add(updatedPanel);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

        mainWindow.add(newPanel);

    }

}
