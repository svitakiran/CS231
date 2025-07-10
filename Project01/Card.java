/*
 * Author: Svita Kiran
 * Date: 2/22/24
 * The purpose of this program is to create a Card for a game of Blackjack.
*/

import java.util.Random;

public class Card {

    /**
     * The value of the card.
     */
    private int value;

    /**
     * Constructs a card with the specified value.
     * @param val
     */
    public Card(int val) {
        if ((1 <= val) && (val < 12)) {
            value = val;
        }
    }

    /**
     * Returns the value of the card.
     * @return the value of the card
     */
    public int getValue() {
        return this.value;
    }
    
    /**
     * Returns a string representation of this card.
     * @return a string representation of this card
     */
    public String toString() {
        String text;
        text = String.valueOf(value);
        return text;
    }
}