package edu.gonzaga;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandPanelTest {

    @Test
    void testHandLength() {
        HandPanel currHand = new HandPanel(0, 0);
        int[] handValues;
        handValues = currHand.getHandValues();

        int expected = 5;

        assertEquals(handValues.length, expected);
    }

    @Test
    void testVisibility() {
        HandPanel currPanel = new HandPanel(0, 0);
        currPanel.hidePanel();
        boolean check = currPanel.isVisible();

        boolean expected = false;

        assertEquals(check, expected);
    }

    @Test
    void testEnabled() {
        HandPanel currPanel = new HandPanel(0, 0);
        currPanel.enableAll();
        boolean check = currPanel.isEnabled();

        boolean expected = true;

        assertEquals(check, expected);
    }

}
