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

import java.util.ArrayList;

/**Class to identify score patterns*/
public class Scores {
    /**
     Gives the score for the lower score card by checking
     if hand meets requirements
     *
     * @param hand
     */
    int maxOfKind(ArrayList<String> hand) {
        int maxCount = 0;
        int currentCount ;
        for (int dieValue = 1; dieValue <=6; dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++)
            {
                if (hand.get(diePosition).equals(Integer.toString(dieValue)))
                    currentCount++;
            }
            if (currentCount > maxCount)
                maxCount = currentCount;
        }
        return maxCount;
    }

    /**
     Checks is house if full or not
     *
     * @param hand
     * @return foundFH
     */
    Boolean fullHouse(ArrayList<String> hand) {
        Boolean foundFH = false;
        Boolean found3K = false;
        Boolean found2K = false;
        int currentCount ;
        for (int dieValue = 1; dieValue <=6; dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++)
            {
                if (hand.get(diePosition).equals(Integer.toString(dieValue)))
                    currentCount++;
            }
            if (currentCount == 2)
                found2K = true;
            if (currentCount == 3)
                found3K = true;
        }
        if (found2K && found3K)
            foundFH = true;
    
        return foundFH;
    }

    /**
     Finds length of the longest hand straight
     *
     * @param hand
     * @return maxLength
     */
    int straight(ArrayList<String> hand) {
        int maxLength = 1;
        int curLength = 1;

        for(int counter = 0; counter < 4; counter++)
        {
            if(Integer.valueOf(hand.get(counter)) + 1 == Integer.valueOf(hand.get(counter + 1)))
                curLength++;
            else if (Integer.valueOf(hand.get(counter)) + 1 < Integer.valueOf(hand.get(counter + 1))) //jump of >= 2
                curLength = 1;
            if (curLength > maxLength)
                maxLength = curLength;
        }

        return maxLength;
    }

    /**
     Finds total value of dice in hand
     *
     * @param hand
     * @return total
     */
    int totalDice(ArrayList<String> hand) {
        int total = 0;

        for (int diePosition = 0; diePosition < 5; diePosition++)
        {
            total += Integer.valueOf(hand.get(diePosition));
        }
        return total;
    }
}