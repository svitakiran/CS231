/*
 * Author: Svita Kiran
 * Date: 2/22/24
 * The purpose of this program is to simulate 1000 games of Blackjack.
*/

public class Simulation {

    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack();
        int playerWins = 0;
        int dealerWins = 0;
        int ties = 0;

        for (int i = 0; i < 1000; i++) {
            int result = blackjack.game(false);
            if (result == 1) {
                playerWins++;
            } else if (result == -1) {
                dealerWins++;
            } else {
                ties++;
            }
        }

        System.out.println("----------------------------------");
        System.out.print("Total player wins: ");
        System.out.println(playerWins);
        System.out.print("Player win percentage: ");
        System.out.println(playerWins/10 + "%");
        System.out.print("Total dealer wins: ");
        System.out.println(dealerWins);
        System.out.print("Dealer win percentage: ");
        System.out.println(dealerWins/10 + "%");
        System.out.print("Total ties: ");
        System.out.println(ties);
        System.out.print("Tie percentage: ");
        System.out.println(ties/10 + "%");
        System.out.println("----------------------------------");
    }
}
