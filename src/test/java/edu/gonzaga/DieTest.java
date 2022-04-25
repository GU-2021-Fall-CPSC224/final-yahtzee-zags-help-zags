/**
 * This program plays an abreviated game of Yahtzee with the user
 *
 * CPSC 224 Spring 2022
 * Homework Assignment #3
 * No Sources to cite
 *
 * @author Jesse Adams
 * @versin v3.0 03/04/2022
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
}