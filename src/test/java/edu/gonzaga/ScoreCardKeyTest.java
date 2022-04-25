package edu.gonzaga;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCardKeyTest {

    @Test
    void keyList() throws IOException {
        ArrayList<String> hand = new ArrayList<>();
        hand.add("1");
        hand.add("1");
        hand.add("1");
        hand.add("1");
        hand.add("1");

        PlayerScoreStatus currentScores = new PlayerScoreStatus(1);
        currentScores.createPlayerScoreFile();
        ScoreCardKey scoreKey = new ScoreCardKey(hand, currentScores);
        ArrayList<ArrayList<String>> key = scoreKey.getKey();
        assertEquals("1", key.get(0).get(0));
    }

    @Test
    void selectKeyTest() throws IOException {
        ArrayList<String> hand = new ArrayList<>();
        hand.add("1");
        hand.add("1");
        hand.add("1");
        hand.add("1");
        hand.add("1");

        PlayerScoreStatus currentScores = new PlayerScoreStatus(1);
        currentScores.createPlayerScoreFile();
        ScoreCardKey scoreKey = new ScoreCardKey(hand, currentScores);
        assertEquals("", scoreKey.pickKeyCode("20"));
    }
}
