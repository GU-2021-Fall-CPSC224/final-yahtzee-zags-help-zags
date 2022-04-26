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

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 */
public class PlayerScoreStatus {
    private Integer playerNum;

    /**
     * Constructor for the PlayerScoreStatus class. Initializes the 
     * playerNum field.
     * 
     * @param playerNum - the player's number
     * @return void
     */
    public PlayerScoreStatus(Integer playerNum) {
        this.playerNum = playerNum;
    }

    /**
     * Creates a file to store the player's score and status.
     * 
     * @param void
     * @return void
     * @throws IOException
     */
    void createPlayerScoreFile() throws FileNotFoundException {
        PrintWriter playerScore = new PrintWriter("scorecard" + playerNum + ".txt");

        for(int i = 1; i <= 6; i++) {
            playerScore.write(Integer.toString(i));
            playerScore.write(",n,u,0" + "\n");
        }
        playerScore.write("3k,n,l,0" + "\n");
        playerScore.write("4k,n,l,0" + "\n");
        playerScore.write("FH,n,l,0" + "\n");
        playerScore.write("SS,n,l,0" + "\n");
        playerScore.write("LS,n,l,0" + "\n");
        playerScore.write("Y,n,l,0" + "\n");
        playerScore.write("C,n,l,0" + "\n");
        playerScore.close();
    }

    /**
     * Reads the player's score file and returns the contents as an ArrayList.
     * 
     * @param void
     * @return Arraylist<String> - stores the contents of the player's score file
     * @throws IOException
     */
    ArrayList readScoreCard() throws IOException {
        FileReader file = new FileReader("scorecard" + playerNum + ".txt");
        Scanner input = new Scanner(file);
        ArrayList<ArrayList<String>> cardList = new ArrayList<ArrayList<String>>();

        while(input.hasNextLine()) {
            String[] find = input.nextLine().split(",");
            ArrayList<String> line = new ArrayList<String>(Arrays.asList(find));
            cardList.add(line);
        }

        return cardList;
    }

    /**
     * Update the player's scorecard file with the new score.
     * 
     * @param updatedCardList
     * @return void
     * @throws IOException
     */
    void updateScoreCard(ArrayList<ArrayList<String>> updatedCardList) throws IOException {
        FileWriter output = new FileWriter("scorecard" + playerNum + ".txt");
        for(int i = 0; i < updatedCardList.size(); i++) {
            output.write(updatedCardList.get(i).get(0) + ",");
            output.write(updatedCardList.get(i).get(1) + ",");
            output.write(updatedCardList.get(i).get(2) + ",");
            output.write(updatedCardList.get(i).get(3));
            output.write("\n");
        }
        output.close();
    }

    /**
     * Checks to see which score lines are still open.
     * 
     * @param void
     * @return count - the number of open lines
     * @throws IOException
     */
    Integer checkScoreLinesAvailable() throws IOException {
        ArrayList<ArrayList<String>> currentScores = readScoreCard();
        int count = 0;
        for(int i = 0; i < currentScores.size(); i++) {
            if (currentScores.get(i).get(1).equals("n")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Getter for the playerNum field.
     * 
     * @param void
     * @return int - the player's number
     */
    public int getPlayerNum() {
        return playerNum;
    }
}