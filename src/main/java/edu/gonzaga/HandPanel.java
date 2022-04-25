/**
 * GUI game of Yahtzee!
 * 
 * CPSC 224, Spring 2022
 * Final project
 * Sources: Dr. Aaron Crandall's DiceImages class from the class
 *  gitHub repo
 * 
 * @author Zags Help Zags team
 * @version v1.0, 4/24/2022
*/

package edu.gonzaga;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.*;

/**
 * Displays and handles yahtzee rolling functionality.
 * 
 * @author Jesse Adams
 * @see 
 */
public class HandPanel extends JPanel implements ActionListener
{
    private int sizeX; // width of the panel
    private int sizeY; // height of the panel
    private int locationX; // x-coordinate of the panel
    private int locationY; // y-coordinate of the panel
    private int currRollCount; // number of times the die has been rolled
    private JButton rollButton; // button to roll dice
    private ArrayList<Die> hand; // list of dice in the hand
    private ArrayList<JLabel> diceImages; // list of dice images
    private ArrayList<JCheckBox> handCheckBoxes; // list of checkboxes for dice in the hand
    private DiceImages diceImagesObj; // object to hold dice images

    /**
     * 
     */
    public HandPanel(int locationX, int locationY)
    {
        this.sizeX = 200;
        this.sizeY = 340;
        this.locationX = locationX;
        this.locationY = locationY;
        this.currRollCount = 0;
        this.diceImagesObj = new DiceImages("YahtzeeMedia/Dice");
        this.makePanel();
    }

    /**
     * Returns the values of the dice in the hand as an 
     * array of integers that can then be used for scoring.
     * 
     * @param void
     * @return int[] - array of dice values
     */
    public int[] getHandValues()
    {
        int[] handValues = new int[this.hand.size()];

        for (int i = 0; i < this.hand.size(); i++)
        {
            handValues[i] = this.hand.get(i).getDieValue();
        }

        return handValues;
    }

    /**
     * 
     */
    public void actionPerformed(ActionEvent e)
    {
        this.roll();
    }

    /**
     * 
     */
    private void makePanel()
    {
        this.rollButton = new JButton("Roll!");
        this.rollButton.setBounds(10, 270, 150, 50);
        this.rollButton.addActionListener(this);
        this.rollButton.setVisible(true);

        this.setBounds(this.locationX, this.locationY, this.sizeX, this.sizeY);
        this.setLayout(null);
        this.setBackground(Color.orange);

        this.add(this.rollButton);
        this.initHand();

        // make panel visible at the end
        this.setVisible(true);
    }

    /**
     * Initializes the hand field by adding the appropriate number of 
     * Die objects to the hand ArrayList. 
     * 
     * @param void
     * @return void
     */
    private void initHand()
    {
        this.hand = new ArrayList<>();
        this.diceImages = new ArrayList<>();
        this.handCheckBoxes = new ArrayList<>();

        for (int i = 0; i < 5; i++)
        {
            Die die = new Die(6);
            hand.add(die);

            // initialize dice images (they are lables)
            JCheckBox dieCheckBox = new JCheckBox("Keep Die");
            dieCheckBox.setBounds(60, (50 * i + 23), 100, 25);
            dieCheckBox.setForeground(Color.black);
            dieCheckBox.setEnabled(false);
            this.handCheckBoxes.add(dieCheckBox);
            this.add(dieCheckBox);

            // init check boxes
            JLabel imageLabel = new JLabel(this.diceImagesObj.getDieImage(1));
            imageLabel.setBounds(10, (50 * i + 10), 50, 50);
            this.diceImages.add(imageLabel);
            this.add(this.diceImages.get(i));
        }
    }

    /**
     * Rolls all 5 dice in players hand and updates their values in
     * the diceHand[] array
     * 
     * @param void
     * @return void
     */
    private void roll()
    {
        this.currRollCount++; // increment the roll count once the method is called

        if (this.currRollCount <= 3)
        {
            for (int i = 0; i < this.hand.size(); i++)
            {
                if (!this.handCheckBoxes.get(i).isSelected())
                {
                    this.hand.get(i).rollDie();
                    this.diceImages.get(i).setIcon(this.diceImagesObj.getDieImage(this.hand.get(i).getDieValue()));
                }
            }
        }

        if (this.currRollCount == 3)
        {
            this.rollButton.setEnabled(false);
            this.disableCheckBoxes();
            this.currRollCount = 1;
        }
        else if (this.currRollCount == 1)
        {
            // this.scoreHandButton.setEnabled(true);
            this.enableCheckBoxes();
        }
    }

    /**
     * Toggles the enabled state of the checkboxes in the handPanel.
     * 
     * @param void
     * @return void
     */
    private void enableCheckBoxes()
    {
        for (int i = 0; i < this.handCheckBoxes.size(); i++)
        {
            this.handCheckBoxes.get(i).setEnabled(true);
        }
    }

    /**
     * Toggles the enabled state of the checkboxes in the handPanel and unchecks 
     * all checkboxes.
     * 
     * @param void
     * @return void
     */
    private void disableCheckBoxes()
    {
        for (int i = 0; i < this.handCheckBoxes.size(); i++)
        {
            this.handCheckBoxes.get(i).setEnabled(false);
            this.handCheckBoxes.get(i).setSelected(false);
        }
    }

    /**
     * 
     */
    private class DiceImages
    {
        public ArrayList<ImageIcon> images;
    
        public DiceImages(String imagesPath)
        {
            this.images = new ArrayList<>(12);
            this.loadImages(imagesPath);
        }

        public ImageIcon getDieImage(int dieValue)
        {
            return this.images.get(dieValue);
        }

        private void loadImages(String imagesPath)
        {
            BufferedImage currPicture;
            images.add(null);

            for( int i = 1; i < 13; i++)
            {
                try
                {
                    if (i < 10)
                    {
                        String filename = imagesPath + "/D6-0" + i + ".png";
                        currPicture = ImageIO.read(new File(filename));
                    }
                    else // if (i < 9)
                    {
                        String filename = imagesPath + "/D6-" + i + ".png";
                        currPicture = ImageIO.read(new File(filename));
                    }

                    Image dimg = currPicture.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    ImageIcon scaledImage = new ImageIcon(dimg);
                    images.add(scaledImage);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}