/**
 * GUI game of Yahtzee!
 * 
 * CPSC 224, Spring 2022
 * Final project
 * Sources: Dr. Aaron Crandall's DiceImages class from the class
 *  gitHub repo
 *  - Crandall lecture 01 slides for Yahtzee game rules
 * 
 * @author Zags Help Zags team
 * @version v1.0, 4/24/2022
*/

package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main GUI class for Yahtzee! 
 * 
 * @author Zags Help Zags team
 */
public class MainGUI {

    JFrame mainWindow;
    public Integer result;
    private Integer playerTracker = 1;
    private final Color realOrange = new Color(255, 127, 0);

    /**
     * Constructor for the MainGUI class.
     * 
     * @param void
     * @return void
     */
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

        ImageIcon pumpkinLogo = new ImageIcon("./YahtzeeMedia/Logos/pumpking.jpeg");
        JPanel pumpkinPanel = new JPanel();
        pumpkinPanel.setBackground(realOrange);
        pumpkinPanel.add(new JLabel(pumpkinLogo));
        mainWindow.add(pumpkinPanel);
        pumpkinPanel.setVisible(true);

        // Start Screen
        mainWindow.setVisible(true);

        JPanel myPanel = new JPanel();
        myPanel.setBackground(realOrange);
        mainWindow.add(myPanel);

//        ImageIcon pumpkinLogo = new ImageIcon("./YahtzeeMedia/Logos/pumpking.jpeg");
//        JPanel pumpkinPanel = new JPanel();
//        pumpkinPanel.setBackground(realOrange);
//        pumpkinPanel.add(new JLabel(pumpkinLogo));
//        myPanel.add(pumpkinPanel);
//        pumpkinPanel.setVisible(true);

        // Button
        JButton playButton = new JButton("Play");
        playButton.setForeground(realOrange);
        playButton.setBackground(Color.black);
        myPanel.add(playButton);
        Dimension playButtonSize = playButton.getPreferredSize();
        playButton.setBounds(290, 550, playButtonSize.width, playButtonSize.height);
        playButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    myPanel.setVisible(false);
                    pumpkinPanel.setVisible(false);
                    showRules();
                }
            }
        );

        myPanel.setVisible(true);
    }

    /**
     * Shows the rules of Yahtzee on a panel.
     * 
     * @param void
     * @return void
     */
    void showRules() {
        JPanel myPanel = new JPanel();
        LayoutManager layout = new BoxLayout(myPanel, BoxLayout.Y_AXIS);
        myPanel.setLayout(layout);
        myPanel.setBackground(Color.black);
        mainWindow.add(myPanel);

        JLabel rulesLabel = new JLabel("<html> Rules of Yahtzee! <br> YOUR 1ST ROLL: <br> - Roll all 5 dice <br> - If you get the exact combo you want, mark your scorecard <br> - Otherwise, continue with your 2nd roll <br> YOUR 2ND ROLL: <br> - Set aside any \"keepers\" and re-roll the rest <br> - Hate them all? Re-roll them all! <br> - If you get the exact combo you need, mark your scorecard <br> - Otherwise, continue to your 3rd roll <br> YOUR 3RD AND FINAL ROLL: <br> If you still haven't nailed a combo, or just want to maximize your score <br> - Roll some or all of your dice (as above), even any \"keepers\" you might have set aside <br> - You MUST enter a score at the end of this roll, whether or not you like your dice! <br> - If your roll doesn't suit any of your empty boxes, you have to enter a ZERO somewhere <br> - BUT scoring even a single die value is better than a ZERO </html>"); 
        rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rulesLabel.setForeground(realOrange);
        myPanel.add(rulesLabel);

        JButton confirmEntry = new JButton("Play Game!");
        confirmEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmEntry.setBackground(realOrange);
        confirmEntry.setForeground(Color.black);
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

    /**
     * Allows the players to choose how many players are in the game, 
     * between 1 and 4 via a drop down menue.
     * 
     * @param void
     * @return void 
     */
    void selectPlayerAmt() {
        JPanel myPanel = new JPanel();
        myPanel.setBackground(realOrange);
        mainWindow.add(myPanel);

        JLabel selectAmtLabel = new JLabel("Select amount of players: ");
        selectAmtLabel.setForeground(Color.black);
        myPanel.add(selectAmtLabel);

        JComboBox selectNumPlayers = new JComboBox();
        for(int i = 1; i <= 4; i++) {
            selectNumPlayers.addItem(i);
        }
        myPanel.add(selectNumPlayers);

        JButton confirmEntry = new JButton("Enter");
        confirmEntry.setBackground(Color.black);
        confirmEntry.setForeground(realOrange);
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
                        PlayerScoreStatus scoreStatus = new PlayerScoreStatus(playerTracker);
                        playerTurn(scoreStatus);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    myPanel.setVisible(false);
                }
            }
        );
    }

    /**
     * Creates the scorecards for each player
     * 
     * @param void
     * @return void
     * @throws FileNotFoundException
     */
    void createScorecards() throws FileNotFoundException {
        for (int i = 1; i <= result; i++){
            PlayerScoreStatus currentPlayerScoreStatus = new PlayerScoreStatus(i);
            currentPlayerScoreStatus.createPlayerScoreFile();
        }
    }

    /**
     * Displays all info, scorecard and dice to roll for an individual 
     * player's turn.
     * 
     * @param scoreStatus
     * @throws IOException
     */
    void playerTurn(PlayerScoreStatus scoreStatus) throws IOException {
        HandPanel handPanel = new HandPanel(350, 0);
        mainWindow.add(handPanel);

        //PlayerScoreStatus scoreStatus = new PlayerScoreStatus(playerNum);

        JPanel currentPanel = showScoreCard(scoreStatus);
        mainWindow.add(currentPanel);

        JButton nextButton = new JButton("Choose Score");
        currentPanel.add(nextButton);
        nextButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    handPanel.hidePanel();
                    ArrayList<String> diceStrings = new ArrayList<>();
                    int[] diceArray = handPanel.getHandValues();
                    for (int i = 0; i < 5; i++){
                        diceStrings.add(Integer.toString(diceArray[i]));
                    }

                    try {
                        JPanel updatedPanel = updateScores(diceStrings, scoreStatus);
                        currentPanel.setVisible(false);
                        mainWindow.add(updatedPanel);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        );
    }

    /**
     * creates an up to date panel containing the current score
     *
     * @return cardPanel
     */
    JPanel showScoreCard(PlayerScoreStatus scores) throws IOException {
        ArrayList<ArrayList<String>> currentScores = new ArrayList<ArrayList<String>>();
        currentScores = scores.readScoreCard();

        JPanel cardPanel = new JPanel();
        JLabel playerLabel = new JLabel("Player " + scores.getPlayerNum() + ":");
        cardPanel.add(playerLabel);
        JLabel titleLabel = new JLabel("Line        Score");
        cardPanel.add(titleLabel);

        int subtotal = 0;
        for(int i = 0; i < 6; i++) {
            JLabel upperLabel = new JLabel(currentScores.get(i).get(0) + "              " + currentScores.get(i).get(3));
            subtotal += Integer.valueOf(currentScores.get(i).get(3));
            cardPanel.add(upperLabel);
        }
        int bonus = 0;
        if(subtotal > 63) {
            bonus = 35;
        }
        int upperTotal = bonus + subtotal;
        JLabel subtotalLabel = new JLabel("Sub Total      " + subtotal);
        JLabel bonusLabel = new JLabel("Bonus          " + bonus);
        JLabel uppertotalLabel = new JLabel("Upper Total    " + upperTotal);
        cardPanel.add(subtotalLabel);
        cardPanel.add(bonusLabel);
        cardPanel.add(uppertotalLabel);

        int lowerTotal = 0;
        for(int i = 6; i < 13; i++) {
            JLabel lowertotalLabel = new JLabel(currentScores.get(i).get(0) + "             " + currentScores.get(i).get(3));
            lowertotalLabel.setForeground(Color.black);
            cardPanel.add(lowertotalLabel);
            lowerTotal += Integer.valueOf(currentScores.get(i).get(3));
        }
        int grandTotal = upperTotal + lowerTotal;
        JLabel lowerLabel = new JLabel("Lower Total    " + lowerTotal);
        JLabel grandtotalLabel = new JLabel("Grand Total    " + grandTotal);
        cardPanel.add(lowerLabel);
        cardPanel.add(grandtotalLabel);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(realOrange);

        return cardPanel;
    }


    /**
     * creates panel for user to view and input the score code for the roll
     * gives information to ScoreStatus updateScores()
     * Goes to next method loopGame()
     *
     * @param hand - arraylist of String
     * @param playerStatus
     * @throws IOException
     */
    JPanel updateScores(ArrayList<String> hand, PlayerScoreStatus playerStatus) throws IOException {
        // key with correct scores
        JPanel newPanel = new JPanel();

        ScoreCardKey keyValues = new ScoreCardKey(hand, playerStatus);
        ArrayList<ArrayList<String>> keyList = keyValues.getKey();

        JComboBox choices = new JComboBox();
        // show score options
        for(int i = 0; i < keyList.size(); i++) {
            if(keyList.get(i).get(1).equals("n")) {
                JLabel newLabel = new JLabel("score is " + keyList.get(i).get(3) + " if you choose " + keyList.get(i).get(0) + " line");
                newPanel.add(newLabel);
                choices.addItem(keyList.get(i).get(0));
            }
        }
        newPanel.add(choices);

        JButton buttonSelect = new JButton("Enter");
        newPanel.add(buttonSelect);
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));

        ArrayList<ArrayList<String>> currentScores = playerStatus.readScoreCard();
        buttonSelect.addActionListener(e -> {
            String result = (String)choices.getSelectedItem();
            String points = keyValues.pickKeyCode(result);

            if(points != "") {
                for (int i = 0; i < currentScores.size(); i++) {
                    if (currentScores.get(i).get(1).equals("n")) {
                        if (currentScores.get(i).get(0).equals(result)) {
                            currentScores.get(i).set(1, "y");
                            currentScores.get(i).set(3, points);
                            break;
                        }
                    }
                }
            }
            try {
                playerStatus.updateScoreCard(currentScores);
                newPanel.setVisible(false);
//                playerTracker++;
//                PlayerScoreStatus newScoreStatus = new PlayerScoreStatus(playerTracker % Integer.parseInt(result));
                checkPlayerTurns();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        return newPanel;
    }

    /**
     * Increments or the playerTracker field and resets it if player 
     * 1 is up again. 
     * 
     * @param void
     * @return void
     * @throws IOException
     */
    void checkPlayerTurns() throws IOException {
        if(playerTracker.equals(result)) {
            playerTracker = 1;
        }
        else {
            playerTracker++;
        }
        PlayerScoreStatus scoreStatus = new PlayerScoreStatus(playerTracker);
        if (!scoreStatus.checkScoreLinesAvailable().equals(0)) {
            playerTurn(scoreStatus);
        }
        else {
            JPanel finalScreenPanel = new JPanel();
            LayoutManager finalScreenLayout = new BoxLayout(finalScreenPanel, BoxLayout.Y_AXIS);
            finalScreenPanel.setLayout(finalScreenLayout);
            JPanel scoresPanel = new JPanel();
            LayoutManager layout = new BoxLayout(scoresPanel, BoxLayout.X_AXIS);
            scoresPanel.setLayout(layout);
            JButton playAgain = new JButton("Play Again");
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(playAgain);
            for(int i = 1; i <= result; i++) {
                JPanel playerScore = new JPanel();
                PlayerScoreStatus currentStatus = new PlayerScoreStatus(i);
                playerScore.add(showScoreCard(currentStatus));
                scoresPanel.add(playerScore);
            }
            finalScreenPanel.add(scoresPanel);
            finalScreenPanel.add(buttonPanel);
            finalScreenPanel.setBackground(Color.black);
            mainWindow.add(finalScreenPanel);

            // play again action
            playAgain.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        finalScreenPanel.setVisible(false);
                        result = 0;
                        mainWindow.setVisible(false);
                        MainGUI newGUI = new MainGUI();
                    }
                }
            );
        }
    }

    Integer getPlayerTracker() {
        return playerTracker;
    }
}