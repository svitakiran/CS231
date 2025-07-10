/*
 * Author: Svita Kiran
 * Date: 2/22/24
 * The purpose of this program is to simulate one game of Blackjack.
*/


public class Blackjack {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private int shuffle;

    /**
     * @param shuffle the number of cards in the deck to shuffle
     */
    public Blackjack(int shuffle) {
        this.shuffle = shuffle;
        reset();
    }

    /**
     * setting up a new game with a default reshuffle cutoff of 15
     */
    public Blackjack() {
        this(15);
    }

    /**
     * resets the game and creates a new deck and hand for both players
     */
    public void reset() {
        deck = new Deck();
        if (deck.size() < shuffle) {
            deck.shuffle();
        }
        playerHand = new Hand();
        dealerHand = new Hand();
    }

    /**
     * deals out two cards to both players
     */
    public void deal() {
        playerHand.add(deck.deal());
        dealerHand.add(deck.deal());
        playerHand.add(deck.deal());
        dealerHand.add(deck.deal());
    }

    /**
     * returns true or false depending on what the player gets
     */
    public boolean playerTurn() {
        while (playerHand.getTotalValue() < 16) {
            playerHand.add(deck.deal());
        }
        return playerHand.getTotalValue() <= 21;
    }

    /**
     * returns true or false depending on what the dealer gets
     */
    public boolean dealerTurn() {
        while (dealerHand.getTotalValue() < 17) {
            dealerHand.add(deck.deal());
        }
        return dealerHand.getTotalValue() <= 21;
    }

    /**
     * sets the reshuffle cutoff value.
     * @param cutoff the new cutoff value
     */
    public void setReshuffleCutoff(int cutoff) {
        shuffle = cutoff;
    }

    /**
     * returns the current reshuffle cutoff value
     */
    public int getReshuffleCutoff() {
        return shuffle;
    }

    /**
     * returns a specific string with the player and dealer's hands and totals
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player Hand: ").append(playerHand).append(" (Total: ").append(playerHand.getTotalValue()).append(")\n");
        sb.append("Dealer Hand: ").append(dealerHand).append(" (Total: ").append(dealerHand.getTotalValue()).append(")\n");
        return sb.toString();
    }

    /**
     * runs one game of Blackjack
     * @param verbose is a boolean
     */
    public int game(boolean verbose) {
        reset();
        deal();
        if (!playerTurn()) {
            if (verbose) {
                System.out.println("Player busts. Dealer wins!");
                System.out.println(this);
            }
            return -1;
        }
        if (!dealerTurn()) {
            if (verbose) {
                System.out.println("Dealer busts. Player wins!");
                System.out.println(this);
            }
            return 1;
        }

        int playerTotal = playerHand.getTotalValue();
        int dealerTotal = dealerHand.getTotalValue();
        if (playerTotal > dealerTotal) {
            if (verbose) {
                System.out.println("Player wins!");
                System.out.println(this);
            }
            return 1;
        } else if (playerTotal < dealerTotal) {
            if (verbose) {
                System.out.println("Dealer wins!");
                System.out.println(this);
            }
            return -1;
        } else {
            if (verbose) {
                System.out.println("Push!");
                System.out.println(this);
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Blackjack play = new Blackjack();
        play.game(true);
        System.out.println();
        play.game(true);
        System.out.println();
        play.game(true);
        System.out.println();
    }
}