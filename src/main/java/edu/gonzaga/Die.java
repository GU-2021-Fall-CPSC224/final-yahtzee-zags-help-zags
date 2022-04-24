package edu.gonzaga;

import java.util.Random;

public final class Die
{
    private int numSides;
    private int sideUp;
    private Random rand = new Random();

    /**
     * Constructor for a Die class object, sets dieValue to 
     * to the value of the constant SIDE_UP
     * 
     * @param N/A
     * @returns N/A
     */
    public Die(int numSides)
    {
        this.numSides = numSides;
        this.sideUp = 1;
    }

    /**
     * Setter for the numSides field.
     * 
     * @param newNumSides
     * @return void
     */
    public void setNumSides(int newNumSides)
    {
        this.numSides = newNumSides;
    }

    /**
     * Getter for dieValue attribute
     * 
     * @param void
     * @return this.dieValue
     */
    public int getDieValue()
    {
        return this.sideUp;
    }

    /**
     * Setter for sideUp attribute.
     * 
     * @param newValue - value to set sideUp to.
     * @return void
     */
    public void setSideUp(int newValue)
    {
        this.sideUp = newValue;
    }

    /**
     * Rolls the die by generating a new random number and
     * assigning dieValue to that new random number
     * 
     * @param void
     * @return void
     */
    public void rollDie()
    {
        this.sideUp = this.rand.nextInt(numSides) + 1;
    }
}