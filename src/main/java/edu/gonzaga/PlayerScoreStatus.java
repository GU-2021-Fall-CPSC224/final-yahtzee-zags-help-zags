package edu.gonzaga;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PlayerScoreStatus {
    private Integer playerNum;

    public PlayerScoreStatus(Integer playerNum) {
        this.playerNum = playerNum;
    }

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

    public int getPlayerNum() {
        return playerNum;
    }

}
