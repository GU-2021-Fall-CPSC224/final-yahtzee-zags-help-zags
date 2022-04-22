package edu.gonzaga;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**Class to interact with score files*/
public class ScoreStatus {

    /**
     Resets score card for new game
     *
     * @return originalCardList
     */
    ArrayList readScoreCard() throws IOException {
        FileReader file = new FileReader("scorecard.txt");
        Scanner input = new Scanner(file);

        ArrayList<ArrayList<String>> originalCardList = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> cardList = new ArrayList<ArrayList<String>>();
        int i = 0;
        while(input.hasNextLine()) {
            String[] find = input.nextLine().split(",");
            ArrayList<String> line = new ArrayList<String>(Arrays.asList(find));
            originalCardList.add(line);
            i++;
        }

        return originalCardList;
    }

    /**
     writes updated score to scorecard.txt
     *
     * @param updatedCardList
     */
    void updateScoreCard(ArrayList<ArrayList<String>> updatedCardList) throws IOException {
        FileWriter output = new FileWriter("scorecard.txt");
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
     Resets ScoreCard file by overwriting any existing concents for
     * a new game
     *
     */
    void resetScoreCard() throws IOException {
        FileWriter output = new FileWriter("scorecard.txt");

        for(int i = 1; i <= 6; i++) {
            output.write(Integer.toString(i));
            output.write(",n,u,0" + "\n");
        }
        output.write("3k,n,l,0" + "\n");
        output.write("4k,n,l,0" + "\n");
        output.write("FH,n,l,0" + "\n");
        output.write("SS,n,l,0" + "\n");
        output.write("LS,n,l,0" + "\n");
        output.write("Y,n,l,0" + "\n");
        output.write("C,n,l,0" + "\n");
        output.close();
    }

    /**
     Checks how many score lines used, if 0 then end of game
     *
     * @return count
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
}
