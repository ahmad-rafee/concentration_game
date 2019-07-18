/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.ui;

import concentration.game.core.Card;
import concentration.game.core.Game;
import concentration.game.core.Pair;
import concentration.game.core.PairFullException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Suiri Otaku
 */
// Score Panel controls the PlayGround .
// playground supports the scorepanel with data;
public class ScorePanel extends JPanel {

    Game game;
    JLabel score, status, timer, points, bscore, time, card1, card2;
    JPanel pair;
    Thread tr;Timer td;
    JButton start, sort, shuffle, hint;
    PlayGround pg;
    ImageIcon cover;
    int bestScore, ct = 0, bt = 600;
    boolean timer_trigger = false, won = false;

    public ScorePanel() {

        // setBounds(620,0,300,600);
        Font f = new Font("Courier New", 15, 12);
        // this.cards = new Pair();
        setLayout(new GridLayout(2, 1, 1, 1));

        // panel for cards , panel for all
        pair = new JPanel() {
            {
                setFont(f);
            }
        };
        pair.setLayout(new GridLayout(1, 2, 10, 10));
        card1 = new JLabel() {
            {
                setIcon(cover);
            }
        };
        card2 = new JLabel() {
            {
                setIcon(cover);
            }
        };
        pair.add(card1);
        pair.add(card2);
        add(pair);

        JPanel all = new JPanel();
        all.setLayout(new GridLayout(10, 1, 1, 1));
        status = new JLabel() {
            {
                setFont(f);
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        add(all);
        all.add(status);

        points = new JLabel() {
            {
                setFont(f);
                setForeground(Color.CYAN);
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        all.add(points);
        bscore = new JLabel() {
            {
                setFont(f);
                setForeground(Color.CYAN);
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };

        all.add(bscore);
        time = new JLabel() {
            {
                setFont(f);
                setForeground(Color.CYAN);
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        //setBestTime
        time.setText("Best Time :" + strTime(this.bt));
        all.add(time);
        start = new JButton("Start") {
            {
                setFont(f);
                setForeground(Color.CYAN);
                addActionListener(e -> {
                    // start new game ;
                    pg.startNewGame();
                    resetTimer();
                });

            }
        };
        all.add(start);
        sort = new JButton("Sort") {
            {
                setFont(f);
                setForeground(Color.CYAN);

                addActionListener(e -> {
                    pg.sort();
                });
            }
        };
        all.add(sort);
        shuffle = new JButton("Shuffle") {
            {
                setFont(f);
                setForeground(Color.CYAN);
                addActionListener(e -> {
                    pg.shuffle();
                });
            }
        };
        all.add(shuffle);
        hint = new JButton("Hint") {
            {

                setFont(f);
                setForeground(Color.CYAN);
                addActionListener(e -> {
                    Pair p = pg.getGame().getPair();
                    if (p.getCard1() != null) {
                        pg.hintMatchingCard(p);
                    }
                });
            }
        };
        all.add(hint);

        timer = new JLabel() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
                setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));

            }
        };
        all.add(timer);
        timer_trigger = true;
        td = new Timer(1000,e->{
        startTimer();
        });
        td.setRepeats(false);
        td.start();
        
    }

    public void setPlayGround(PlayGround pg) {
        this.pg = pg;
    }

    public void setPair(Pair p) {

        cleanPairStatus();
        // now pair is full and ready to be validated ..

        card1.setIcon(p.getCard1().getCard().getImage());
        card2.setIcon(p.getCard2().getCard().getImage());
        boolean x = p.validate();
        if (x) {
            this.status.setBorder(BorderFactory.createLineBorder(Color.green, 1, true));

        } else {
            this.status.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
        }
        this.status.setText(x ? "Match" : "Not Match");
        pair.add(card1);
        pair.add(card2);
        this.pair.revalidate();

    }

    private void cleanPairStatus() {
        card1 = new JLabel() {
            {
                setSize(pg.scale());
            }
        };
        card2 = new JLabel() {
            {
                pg.scale();
            }
        };
        this.status.setText("");
        this.status.setBorder(null);
        this.pair.removeAll();
        this.pair.revalidate();
    }

    public void setPoints(int p) {
        this.points.setText(p + "");
    }

    public void cleanAll() {

        this.pair.removeAll();
        this.score.setText("");
        this.status.setText("");
        this.points.setText("");
        this.won = false;
        this.ct = 0;
        this.revalidate();
    }

    private String centrize(String str) {
        return "<html><div style='text-align: center;'>" + str + "</div></html>";

    }

    private String strTime(int sec) {
        String time = "";
        int minutes = 0;
        if (sec < 60) {
            time = ((sec < 10) ? "0" + sec : sec) + "";
        } else {
            minutes = (int) sec / 60;
            sec -= (60 * minutes);
            time = ((minutes < 10) ? "0" + minutes : minutes) + " : " + ((sec < 10) ? sec : "0" + sec) + "";
        }
        return time;
    }

    public void startTimer() {

        tr = new Thread(() -> {
            while (timer_trigger && !won) {
                timer.setText(strTime(ct));
                ct++;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
            tr.stop();
            time.setText("Best Time : " + ((ct < bt) ? strTime(ct) : strTime(bt)));
        });
        tr.start();
    }

    public void resetTimer() {
        tr.interrupt();
        ct = 0;
        timer.setText("0");
        td = new Timer(1000,e->{
        
        startTimer();
        });
        td.setRepeats(false);
        td.start();
        

    }

    public void showScore(int score) {
        this.points.setText("Your Score is : \n" + score);
    }

    public ImageIcon getCover() {
        return cover;
    }

    public void setCover(ImageIcon cover) {
        this.cover = cover;

    }

    public void showCover() {

        card1.setIcon(cover);
        card2.setIcon(cover);
    }

    public void addCard(Pair pair) {
        if (pair.getCard2() == null) {
            this.card1.setIcon(pair.getCard1().getCard().getImage());
            this.card2.setIcon(cover);
        } else {
            setPair(pair);
        }

    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
        this.bscore.setText("Best Score : " + bestScore);
    }

}
