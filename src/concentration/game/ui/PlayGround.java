/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.ui;

/**
 *
 * @author Suiri Otaku
 */
import concentration.game.core.Pair;
import concentration.game.core.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PlayGround extends JPanel implements MouseListener, KeyEventDispatcher {

    private List<CardLabel> cards;
    Game game; // the game
    ScorePanel sp; // score panel 
    boolean cheat = false; // for cheating
    int seed; // shuffle seed
    int sel_pairs = 0; // selected pair
    List<Comparator<CardLabel>> comp;
    int ci = 0; // comparator index

    public PlayGround(List<CardLabel> cards, Game game, ScorePanel sp) { // constructor -> main
        for (CardLabel c : cards) {
            c.addMouseListener(this);
        }
        this.cards = cards;
        this.game = game;
        this.sp = sp;
        sp.setBestScore(game.bestScore());
        setLayout(new GridLayout(6, 9, 10, 10));
        KeyboardFocusManager m = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        m.addKeyEventDispatcher(this);
        deal();
        this.seed = (int) (Math.random() * 1000);

    }

    public void deal() { // deal Cards ( On start button )
        for (CardLabel card : cards) {
            add(card);
            card.fix_dim();
            card.display();
        }
        revalidate();

    }

    public void shuffle() { // shuffle Cards
        Collections.shuffle(cards, new Random(seed));
        //clean();
        deal();
        this.seed = (int) (Math.random() * 1000);

    }

    public void sort() { // sort -> not completed
        ArrayList<CardLabel> aa = new ArrayList();
        try {
            aa = sortCards(0, this.cards, aa);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        this.cards = aa;
        deal();
    }

    public void sortByRank() { // sort -> not completed
        this.cards.sort(comp.get(1));
        ArrayList<CardLabel> aa = new ArrayList();
        try {
            aa = sortCardsBySuit(0, this.cards, aa);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        this.cards = aa;
        deal();
    }

    public void clean() { // clean panel
        removeAll();
    }

    private void removeMatchingPair(Pair pair) { // remove matching pair
        //  this.cards.get(this.cards.indexOf(pair.getCard1())).exclude();
        // this.cards.get(this.cards.indexOf(pair.getCard2())).exclude();
        for (CardLabel s : this.getAll()) {

            if ((s.getId() == pair.getCard1().getId() || s.getId() == pair.getCard2().getId())) {
                {
                    s.exclude();
                    s.setEnabled(false);
                }
            }
        }
        pair.clean();
    }

    private void hidenotMatchingPair(Pair pair) { // remove matching pair

        for (CardLabel s : this.getAll()) {

            if ((s.getId() == pair.getCard1().getId() || s.getId() == pair.getCard2().getId())) {
                s.hideCard();
            }
        }
        pair.clean();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        CardLabel card = (CardLabel) e.getSource();

        if (!isCheatOn() && !card.isShown() && !card.excluded && !card.hinted) {

            //card.showCard();
            this.game.showCard(card);

            if (!this.game.getPair().isFull()) {
                sp.addCard(this.game.getPair());//-> here is the problem
            }
            if (this.game.getPair().isFull()) {
                Timer t = new Timer(1000, f -> {

                    // waitSec();
                    if (this.game.validatePair()) {
                        Pair p = this.game.getPair();
                        // waitSec();
                        sp.setPair(p);
                        //show on Scorepanel
                        removeMatchingPair(this.game.getPair());
                        this.sel_pairs++;
                        if (this.sel_pairs == 27) {
                            // all pairs are selected
                            JOptionPane.showMessageDialog(this, "You win " + game.getPlayer().getScore());
                            sp.won = true;
                            sp.setBestScore(this.game.bestScore());
                            startNewGame();

                            //this.game.bestScore();
                        }

                        this.game.cleanPair();
                    } else {
                        sp.setPair(this.game.getPair());
                        hidenotMatchingPair(this.game.getPair());

                        this.game.cleanPair();

                    }

                    updateScore();
                });

                t.setRepeats(false);
                t.start();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void waitSec() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayGround.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CardLabel> getAll() {
        CardLabel card;

        List<CardLabel> cs = new ArrayList();
        for (Component c : this.getComponents()) {
            card = (CardLabel) c;
            if (card instanceof CardLabel) {
                cs.add(card);
            }
        }
        return cards;

    }

    public Game getGame() {
        return this.game;

    }

    public void startNewGame() {
        this.game.startNewGame();
        for (CardLabel c : getAll()) {
            c.re_init();
        }
        //sort();
    }

    public void hintMatchingCard(Pair p) {

        if (!p.isFull()) {
            Pair pn = p;
            for (CardLabel x : this.getAll()) {
                try {
                    if (x != pn.getCard1() && !x.excluded && x.isEnabled()) {
                        p.addCard(x);
                        if (game.getMatcher().match(pn)) {
                            x.hint();
                            pn.removeCard2();
                            break;
                        } else {
                            pn.removeCard2();
                        }
                    }
                } catch (PairFullException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    public void cheatOn() {

        for (CardLabel card : this.getAll()) {

            if (!card.excluded) {
                card.cheat();
            }
        }
        this.cheat = true;
    }

    public void cheatOff() {

        for (CardLabel card : this.getAll()) {

            if (!card.excluded && !card.shown) {
                card.flipCard();
            }
        }

        this.cheat = false;
    }

    public boolean isCheatOn() {
        return this.cheat;
    }

    public Dimension scale() { // return dimension after rendering
        return new Dimension(this.cards.get(1).getWidth(), this.cards.get(1).getHeight());

    }

    public void setCover(ImageIcon cover) {
        for (CardLabel card : this.cards) {
            card.setCover(cover);
            card.display();
        }
        revalidate();

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
            if (cheat) {

                cheatOff();
            } else {
                cheatOn();
                Timer t = new Timer(3000, s -> {
                    cheatOff();
                });
                t.setRepeats(false);
                t.start();
            }

        }
        if (e.getKeyChar() == 'a') {
            if (cheat) {

                cheatOff();
            } else {
                cheatOn();

            }

        }
        return true;
    }

    private void updateScore() {
        int score = this.game.getPlayer().getScore();
        this.sp.showScore(score);

    }

    public ArrayList<CardLabel> sortCards(int position, List<CardLabel> sc, ArrayList<CardLabel> ss) throws Exception {

        if ((position < 54)) {
            //sorted
            List<CardLabel> x = new ArrayList<CardLabel>(sc.subList(position, position + 9));
            Collections.sort(x, comp.get(ci));
            ss.addAll(x);
            ss.addAll(sortCards(position + 9, sc, ss));
            return ss;
        } else {
            throw new Exception("Finished");

        }

    }

    public ArrayList<CardLabel> sortCardsBySuit(int position, List<CardLabel> sc, ArrayList<CardLabel> ss) throws Exception {

        if ((position < 54)) {
            //sorted
            List<CardLabel> x = new ArrayList<CardLabel>(sc.subList(position, position + 4));
            Collections.sort(x, comp.get(2));
            ss.addAll(x);
            ss.addAll(sortCards(position + 4, sc, ss));
            return ss;
        } else {
            throw new Exception("Finished");

        }

    }

    public List<Comparator<CardLabel>> getComp() {
        return comp;
    }

    public void setComp(List<Comparator<CardLabel>> comp) {
        this.comp = comp;

    }

    public void sortAll() {
        Collections.sort(cards, comp.get(ci));
        deal();

    }

    public int getComparatorIndex() {
        return ci;
    }

    public void setComparatorIndex(int ci) {
        this.ci = ci;
    }

}
