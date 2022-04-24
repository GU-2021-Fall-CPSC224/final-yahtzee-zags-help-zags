package edu.gonzaga;

import javax.swing.JFrame;

public class Yahtzee
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Yahtzee!");

        frame.setBounds(50, 50, 400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(new HandPanel(10, 10));
        frame.setVisible(true);
    }
}