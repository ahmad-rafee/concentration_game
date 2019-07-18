/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.ui;

import concentration.game.core.Card;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.Border;
import concentration.game.core.*;

/**
 *
 * @author Suiri Otaku
 */
public class CardLabel extends JLabel implements Comparable<CardLabel> {

    Card card;
    ImageIcon cover;
    boolean shown = false;
    boolean excluded = false;
    boolean hinted = false;
    int id;
    boolean cheated = false;

    public CardLabel(Card card, int id) {
        this.card = card;
        this.id = id;

    }

    public void fix_dim() {
        this.card.scaleIcon(card.getImage().getIconWidth(), card.getImage().getIconHeight());
        this.cover = new ImageIcon(this.cover.getImage().getScaledInstance(card.getImage().getIconWidth(), card.getImage().getIconHeight(), Image.SCALE_SMOOTH));

    }

    public void flipCard() {
        setIcon(this.cover);
        this.shown = false;
    }

    public void showCard() {
        this.setIcon(this.card.getImage());
        this.shown = true;
    }

    public void cheat() {
        this.setIcon(this.card.getImage());
        cheated = true;

    }

    public void exclude() {
        setEnabled(false);
        this.excluded = true;
    }

    public void mark() {
        Border border = BorderFactory.createLineBorder(Color.red, 1, true);
        this.setBorder(border);
        hinted = true;
    }

    public void hint() {
        Timer t = new Timer(2000, e -> {
            this.setBorder(null);
            hinted = false;
        });
        mark();
        t.setRepeats(false);
        t.start();

    }

    public void hideCard() {

        this.setIcon(this.cover);
        this.shown = false;

    }

    public Card getCard() {
        return card;
    }

    public boolean isShown() {
        return shown;
    }

    public int getId() {
        return id;
    }

    public void setCover(ImageIcon cover) {
        this.cover = cover;
    }

    public void display() {

        if (this.shown) {
            setIcon(this.card.getImage());
        } else {
            setIcon(this.cover);

        }
    }

    public void re_init() {
        this.hideCard();
        this.excluded = false;
        this.setEnabled(true);
        this.hinted = false;

    }

    @Override
    public int compareTo(CardLabel o) {
        return Integer.compare(getId(), o.getId());
    }

    public int compareCards(CardLabel x) {
        int rank = Integer.compare(getCard().getSuitrank(), x.getCard().getSuitrank());
        if (rank == 0) {
            return Integer.compare(getCard().getRank(), x.getCard().getRank());
        } else {
            return rank;
        }
    }

}
