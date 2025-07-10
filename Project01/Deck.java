/*
 * Author: Svita Kiran
 * Date: 2/22/24
 * The purpose of this program is to create a Deck for a game of Blackjack.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    /**
     * Creates the underlying deck as an ArrayList of Card objects. 
     * Calls build() as a subroutine to build the deck itself.
     */
    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
        build();
        shuffle();
    }

    /**
     * Builds the underlying deck as a standard 52 card deck. 
     * Replaces any current deck stored. 
     */
    public void build() {
        this.deck.clear();
        for(int i = 0; i < 4; i++) {
            for(int j = 2; j <= 9; j++) {
                deck.add(new Card(j));
            }
            deck.add(new Card(11));
        }

        for(int i = 0; i < 16; i++) {
            deck.add(new Card(10));
        }
    }

    /**
     * Returns the number of cards left in the deck. 
     * @return the number of cards left in the deck
     */
    public int size() {
        return deck.size();
    }

    /**
     * Returns and removes the first card of the deck.
     * @return the first card of the deck
     */
    public Card deal() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return deck.remove(0);
    }

    /**
     * Shuffles the cards currently in the deck. 
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Returns a string representation of the deck.
     * @return a string representation of the deck
     */
    public String toString() {
        return "Deck{" +
                "cards=" + deck +
                '}';
    }
}