package edu.gonzaga;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ScorePanelGUI {

    /**
     creates an up to date panel containing the current score
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
            cardPanel.add(lowertotalLabel);
            lowerTotal += Integer.valueOf(currentScores.get(i).get(3));
        }
        int grandTotal = upperTotal + lowerTotal;
        JLabel lowerLabel = new JLabel("Lower Total    " + lowerTotal);
        JLabel grandtotalLabel = new JLabel("Grand Total    " + grandTotal);
        cardPanel.add(lowerLabel);
        cardPanel.add(grandtotalLabel);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));

        return cardPanel;
    }


    /**
     creates panel for user to view and input the score code for the roll
     gives information to ScoreStatus updateScores()
     Goes to next method loopGame()
     *
     * @param hand, cardList
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

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        return newPanel;
    }

}
