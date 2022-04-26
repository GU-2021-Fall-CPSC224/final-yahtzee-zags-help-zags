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

import java.io.IOException;
import java.util.ArrayList;

/** Class to store the state of a point configuration for hand. */
public class ScoreCardKey {
    private ArrayList<ArrayList<String>> scoreCardKey;

    /**
     * Constructor for the ScoreCardKey class.
     * 
     * @param hand
     * @param currentScores
     * @throws IOException
     */
    public ScoreCardKey(ArrayList<String> hand, PlayerScoreStatus currentScores) throws IOException {
        //ScoreStatus currentScores = new ScoreStatus();
        ArrayList<ArrayList<String>> cardList = currentScores.readScoreCard();
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

        //upper score card
        for (int dieValue = 1; dieValue <= 6; dieValue++) {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++) {
                if (hand.get(diePosition).equals(Integer.toString(dieValue)))
                    currentCount++;
            }
            for (int j = 0; j < cardList.size(); j++) {
                ArrayList<String> sublist = new ArrayList<>();
                if (cardList.get(j).get(0).equals(Integer.toString(dieValue))) {
                    sublist.add(cardList.get(j).get(0));
                    sublist.add(cardList.get(j).get(1));
                    sublist.add(cardList.get(j).get(2));
                    sublist.add(String.valueOf(dieValue * currentCount));
                    list.add(sublist);
                }
            }
        }

        Scores score = new Scores();
        // lower scorecard
        ArrayList<String> threeOfKind = new ArrayList<>();
        threeOfKind.add(cardList.get(6).get(0));
        threeOfKind.add(cardList.get(6).get(1));
        threeOfKind.add(cardList.get(6).get(2));
        if (score.maxOfKind(hand) >= 3) {
            threeOfKind.add(String.valueOf(score.totalDice(hand)));
        } else {
            threeOfKind.add("0");
        }
        list.add(threeOfKind);

        ArrayList<String> fourOfKind = new ArrayList<>();
        fourOfKind.add(cardList.get(7).get(0));
        fourOfKind.add(cardList.get(7).get(1));
        fourOfKind.add(cardList.get(7).get(2));
        if (score.maxOfKind(hand) >= 4) {
            fourOfKind.add(String.valueOf(score.totalDice(hand)));
        } else {
            fourOfKind.add("0");
        }
        list.add(fourOfKind);

        ArrayList<String> fullH = new ArrayList<>();
        fullH.add(cardList.get(8).get(0));
        fullH.add(cardList.get(8).get(1));
        fullH.add(cardList.get(8).get(2));
        if (score.fullHouse(hand)) {
            fullH.add("25");
        } else {
            fullH.add("0");
        }
        list.add(fullH);

        ArrayList<String> smallS = new ArrayList<>();
        smallS.add(cardList.get(9).get(0));
        smallS.add(cardList.get(9).get(1));
        smallS.add(cardList.get(9).get(2));
        if (score.straight(hand) >= 4) {
            smallS.add("30");
        } else {
            smallS.add("0");
        }
        list.add(smallS);

        ArrayList<String> largeS = new ArrayList<>();
        largeS.add(cardList.get(10).get(0));
        largeS.add(cardList.get(10).get(1));
        largeS.add(cardList.get(10).get(2));
        if (score.straight(hand) >= 5) {
            largeS.add("40");
        } else {
            largeS.add("0");
        }
        list.add(largeS);

        ArrayList<String> yatzeeS = new ArrayList<>();
        yatzeeS.add(cardList.get(11).get(0));
        yatzeeS.add(cardList.get(11).get(1));
        yatzeeS.add(cardList.get(11).get(2));
        if (score.maxOfKind(hand) >= 5) {
            yatzeeS.add("50");
        } else {
            yatzeeS.add("0");
        }
        list.add(yatzeeS);

        ArrayList<String> chanceS = new ArrayList<>();
        chanceS.add(cardList.get(12).get(0));
        chanceS.add(cardList.get(12).get(1));
        chanceS.add(cardList.get(12).get(2));
        chanceS.add(String.valueOf(score.totalDice(hand)));
        list.add(chanceS);

        this.scoreCardKey = list;
    }

    /**
     * Getter for the scoreCardKey field.
     * 
     * @param void
     * @return ArrayList<ArrayList<String>> - the scoreCardKey field
     */
    ArrayList getKey() {
        return this.scoreCardKey;
    }

    /**
     Gets user input string and retrn associated score
     *
     * @param input, keyValues
     * @return associated score
     */
    String pickKeyCode(String input) {
        // key with correct scores
        ArrayList<ArrayList<String>> keyList = getKey();
        for (int i = 0; i < keyList.size(); i++) {
            if (keyList.get(i).get(1).equals("n")) {
                if (keyList.get(i).get(0).equals(input)) {
                    return keyList.get(i).get(3);
                }
            }
        }

        return "";
    }
}