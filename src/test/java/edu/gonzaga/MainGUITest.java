package edu.gonzaga;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainGUITest {

    @Test
    void testVisibility() {
        MainGUI currGUI = new MainGUI();
        currGUI.showRules();

        MainGUI expectedGUI = new MainGUI();
        expectedGUI.mainWindow.setVisible(true);

        assertEquals(currGUI.mainWindow.isVisible(), expectedGUI.mainWindow.isVisible());
    }

    @Test
    void testResult() {
        MainGUI currGUI = new MainGUI();
        Integer expected = null;

        assertEquals(currGUI.result, expected);
    }

    @Test
    void testPlayerTracker() {
        MainGUI currGUI = new MainGUI();
        Integer expected = 1;

        assertEquals(currGUI.getPlayerTracker(), expected);
    }

    @Test
    void testTitle() {
        MainGUI currGUI = new MainGUI();
        String expected = "Yahtzee!";

        assertEquals(currGUI.mainWindow.getTitle(), expected);
    }

}
