package edu.gonzaga;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerScoreStatusTest {

    @Test
    void scorePlayerTest() {
        int playerNum = 1;
        PlayerScoreStatus scorePlayer = new PlayerScoreStatus(playerNum);
        assertEquals(playerNum, scorePlayer.getPlayerNum());
    }

    @Test
    void scorePlayercase2Test() {
        int playerNum = 4;
        PlayerScoreStatus scorePlayer = new PlayerScoreStatus(playerNum);
        assertEquals(playerNum, scorePlayer.getPlayerNum());
    }

    @Test
    void scorePlayercase3Test() {
        int playerNum = 3;
        PlayerScoreStatus scorePlayer = new PlayerScoreStatus(playerNum);
        assertEquals(playerNum, scorePlayer.getPlayerNum());
    }
    
}
