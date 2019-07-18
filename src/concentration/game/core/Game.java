/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.core;

import concentration.game.ui.CardLabel;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Suiri Otaku
 */
public class Game {

    Player pl;
    Pair card_pair;
    MatchStrategy matcher;
    Boolean[] status = new Boolean[2];
    int bestScore = -270;

    public Game() {
        this.pl = new Player();
        this.card_pair = new Pair();
        Arrays.fill(this.status, Boolean.FALSE);
        setStrategy(new Color_Rank());
    }

    public void startNewGame() {
        this.pl = new Player();
        this.card_pair.clean();
        Arrays.fill(status, Boolean.FALSE);
        setStrategy(new Color_Rank());

    }

    public void setStrategy(MatchStrategy strategy) {
        this.matcher = strategy;
    }

    public void showCard(CardLabel card) {
        if (this.card_pair.isAvailable()) {

            try {
                card_pair.addCard(card);
                card.showCard();

                card_pair.status[0] = false; // not match
                card_pair.status[1] = false; // not full

            } catch (PairFullException ex) {
                ex.printStackTrace();
            }

        }/*
         else if (card_pair.isFull()) {
            if (matcher.match(this.card_pair)) {
                System.out.println(pl.getScore() + " Score is ");
                pl.setScore(pl.getScore() + 10);
                card_pair.status[0] = true;
                card_pair.status[1] = true;
                System.out.println("im in else if");
            } else {
                card_pair.status[0] = false; // not match
                card_pair.status[1] = true;  // full
                pl.setScore(pl.getScore() - 1);
                System.out.println("im in else");
            }
        }
        
         */

    }

    public Pair getPair() {
        return this.card_pair;
    }

    public void cleanPair() {
        this.card_pair.clean();
    }

    public boolean validatePair() { // here
        if (card_pair.isFull()) {
            if (matcher.match(this.card_pair)) {
                pl.setScore(pl.getScore() + 10);
                card_pair.status[0] = true;
                card_pair.status[1] = true;

            } else {
                card_pair.status[0] = false; // not match
                card_pair.status[1] = true;  // full
                pl.setScore(pl.getScore() - 1);

            }
        }
        return this.card_pair.validate();
    }

    public MatchStrategy getMatcher() {
        return this.matcher;
    }

    public Player getPlayer() {

        return this.pl;
    }

    public int bestScore() {
        if (this.getPlayer().getScore() >= this.bestScore) {
            bestScore = this.pl.getScore();
        }
        return bestScore;
    }

}
