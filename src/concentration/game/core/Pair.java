/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.core;

import concentration.game.ui.CardLabel;
import java.util.Arrays;

/**
 *
 * @author Suiri Otaku
 */
public class Pair {

    private CardLabel card1, card2;
    public Boolean[] status = new Boolean[2];

    public Pair() {
        Arrays.fill(this.status, Boolean.FALSE);
    }

    public void addCard(CardLabel c) throws PairFullException {

        if (this.card1 == null) {
            this.card1 = c;
        } else if (this.card2 == null) {
            this.card2 = c;
        } else {
            throw new PairFullException();
        }
    }

    public boolean isEmpty() {
        return (this.card1 == null && this.card2 == null);

    }

    public boolean isAvailable() {
        return (this.card1 == null || this.card2 == null);
    }

    public void clean() {
        this.card1 = null;
        this.card2 = null;
        Arrays.fill(this.status, Boolean.FALSE);

    }

    public boolean validate() {
        return (status[0] && status[1]);
    }

    public boolean isFull() {
        return (this.card1 != null && this.card2 != null);
    }

    public CardLabel getCard1() {
        return card1;
    }

    public CardLabel getCard2() {
        return card2;
    }

    public void removeCard2() {
        this.card2 = null;
    }

    @Override
    public String toString() {
        return "Pair{" + "card1=" + card1.getCard().getRank() + ", card2=" + card2.getCard().getRank() + ", status=" + (status[0] && status[1]) + '}';
    }

}
