/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.startpackage;

import concentration.game.core.Suit;
import concentration.game.core.Card;
import concentration.game.ui.CardLabel;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Suiri Otaku
 */
public class Initializer {

    Suit spades, hearts, clubs, diamonds;
    List<Card> cards = new ArrayList();
    List<ImageIcon> covers;

    public Initializer() {
        spades = new Suit(4, "spades", "Black");
        hearts = new Suit(3, "hearts", "Red");
        clubs = new Suit(2, "clubs", "Black");
        diamonds = new Suit(1, "diamonds", "Red");

        File folder = new File("data/");
        File[] files = folder.listFiles();

        addCards(files, 13, 0);
        Card BJoker = new Card(54, new ImageIcon(files[53].getPath())) {
            {

                setSuitrank(0);
                setSuit("Joker");
                setColor("Red");
            }
        };
        cards.add(BJoker);
        cards.addAll(this.spades.getCards());
        cards.addAll(this.hearts.getCards());
        cards.addAll(this.clubs.getCards());
        cards.addAll(this.diamonds.getCards());
        Card RJoker = new Card(54, new ImageIcon(files[52].getPath())) {
            {
                setSuitrank(5);
                setSuit("Joker");
                setColor("Black");
            }
        };
        cards.add(RJoker);
        folder = new File("data/covers/");
        files = folder.listFiles();
        this.covers = new ArrayList();
        setCovers(files);

    }

    private void addCards(File[] files, int rank, int count) {
        try {
            this.clubs.addCard(new Card(rank, new ImageIcon(files[count++].getPath())));
            this.spades.addCard(new Card(rank, new ImageIcon(files[count++].getPath())));
            this.hearts.addCard(new Card(rank, new ImageIcon(files[count++].getPath())));
            this.diamonds.addCard(new Card(rank, new ImageIcon(files[count++].getPath())));
            rank--;
            if (rank != 0) {
                addCards(files, rank, count);
            } else {

            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public List<CardLabel> getCards() {
        List<CardLabel> cs = new ArrayList();
        ImageIcon default_cover = this.getCovers().get(0);
        int i = 1;
        CardLabel tmp;
        for (Card card : this.cards) {

            tmp = new CardLabel(card, i++);
            tmp.setCover(default_cover);
            cs.add(tmp);

        }

        return cs;
    }

    public List<ImageIcon> getCovers() {
        return covers;
    }

    public void setCovers(File[] files) {
        for (File f : files) {
            try {
                this.covers.add(new ImageIcon((new ImageIcon(f.getPath())).getImage().getScaledInstance(this.cards.get(1).getImage().getIconWidth(), this.cards.get(1).getImage().getIconHeight(), Image.SCALE_SMOOTH)));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<Comparator<CardLabel>> getComparators() {
        List<Comparator<CardLabel>> comps = new ArrayList();
        comps.add(new Comparator<CardLabel>() {
            @Override
            public int compare(CardLabel o1, CardLabel o2) {
                //Suits/Ids comparator
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        comps.add(new Comparator<CardLabel>() {
            @Override
            public int compare(CardLabel o1, CardLabel o2) {
                //Suit comparator
                return Integer.compare(o1.getCard().getSuitrank(), o2.getCard().getSuitrank());

            }
        });
        comps.add(new Comparator<CardLabel>() {
            @Override
            public int compare(CardLabel o1, CardLabel o2) {
                //Suit comparator
                return Integer.compare(o1.getCard().getRank(), o2.getCard().getRank());

            }
        });
        return comps;
    }

}
