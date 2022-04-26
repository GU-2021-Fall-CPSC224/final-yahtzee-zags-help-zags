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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testing class for the Die class in yahtzee project.
 * 
 * @author Jesse Adams
 * @see Die.java
 */
public class DieTest
{
    /**
     * Test that if numSides is set to 6, than a 7 will never be rolled.
     */
    @Test
    void sideUpTest()
    {
        Die die = new Die(6);
        die.rollDie();

        Assertions.assertNotEquals(7, die.getDieValue());
    }

    /**
     * Test that if numSides is set to 6, than a 0 will never be rolled.
     */
    @Test
    void sideUpTest2()
    {
        Die die = new Die(6);
        die.rollDie();

        Assertions.assertNotEquals(0, die.getDieValue());
    }
}