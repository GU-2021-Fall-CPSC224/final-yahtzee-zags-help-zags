package edu.gonzaga;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoresTest {

    @Test
    void testTotalDice() {
        ArrayList<String> hand = new ArrayList<>();
        hand.add("2");
        hand.add("2");
        hand.add("2");
        hand.add("1");
        hand.add("3");

        Scores score = new Scores();
        int expected = 10;
        assertEquals(expected, score.totalDice(hand));
    }

    @Test
    void testFullHouseFalse() {
        ArrayList<String> hand = new ArrayList<>();
        hand.add("2");
        hand.add("2");
        hand.add("2");
        hand.add("1");
        hand.add("3");

        Scores score = new Scores();
        Boolean expected = false;
        assertEquals(expected, score.fullHouse(hand));
    }

    @Test
    void testFullHouseTrue() {
        ArrayList<String> hand = new ArrayList<>();
        hand.add("2");
        hand.add("2");
        hand.add("2");
        hand.add("3");
        hand.add("3");

        Scores score = new Scores();
        Boolean expected = true;
        assertEquals(expected, score.fullHouse(hand));
    }

}
