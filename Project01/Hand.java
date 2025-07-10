/*
 * Author: Svita Kiran
 * Date: 2/22/24
 * The purpose of this program is to create a Hand for a game of Blackjack.
*/

import java.util.ArrayList;

public class Hand {

    /**
     * Creates an empty hand as an ArrayList of Cards.  
     */
    private ArrayList<Card> arr;
    public Hand(){
        arr = new ArrayList<>();
    }

    /**
     * Removes any cards currently in the hand. 
     */
    public void reset(){
        arr.clear();
    }

    /**
     * Adds the specified card to the hand.
     * @param card the card to be added to the hand
     */
    public void add(Card card){
        arr.add(card);
    }

    /**
     * Returns the number of cards in the hand.
     * @return the number of cards in the hand
     */
    public int size(){
        return arr.size();
    }

    /**
     * Returns the card in the hand specified by the given index. 
     * @param index the index of the card in the hand.
     * @return the card in the hand at the specified index.
     */
    public Card getCard(int index){
        return arr.get(index);
    }

    /**
     * Returns the summed value over all cards in the hand.
     * @return the summed value over all cards in the hand
     */
    public int getTotalValue(){
        int total = 0;
        for (Card card : arr) {
            total = total + card.getValue();
        }
        return total;
    }

    /**
     * Returns a string representation of the hand.
     * @return a string representation of the hand
     */
    public String toString(){
        return "" + arr + " : " + getTotalValue();
    }
}