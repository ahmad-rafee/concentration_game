/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.ui;

import concentration.game.core.Card;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Suiri Otaku
 */
public class Cover_Chooser extends JFrame implements MouseListener {

    PlayGround pg;
    List<CardLabel> labels;
    List<ImageIcon> covers;
    Container c;
    ScorePanel sp;

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(new Dimension(400, 200)); //To change body of generated methods, choose Tools | Templates.
    }

    public Cover_Chooser(PlayGround pg, List<ImageIcon> covers, ScorePanel sp) {
        this.pg = pg;
        this.covers = covers;
        this.sp = sp;
        this.setLocationRelativeTo(pg);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        init();
    }

    private void init() {

        c = getContentPane();
        c.setLayout(new GridLayout(1, this.covers.size()));
        JLabel li;
        for (ImageIcon image : this.covers) {
            // image = new ImageIcon(image.getImage().getScaledInstance(pg.scale().width,pg.scale().height,Image.SCALE_SMOOTH));
            li = new CardLabel(new Card(0, image), 0);
            li.addMouseListener(this);
            li.setIcon(image);
            add(li);
        }
        setTitle("Choose a cover");
        pack();
        setOpacity((float) 0.9);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void readCovers() {
        this.covers = new ArrayList();
        File[] images = (new File("data/covers/")).listFiles();
        for (File f : images) {

            this.covers.add(new ImageIcon(f.getPath()));

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        CardLabel label = (CardLabel) e.getSource();
        this.pg.setCover(label.card.getImage());
        this.sp.setCover(label.card.getImage());
        this.sp.showCover();
        for (Component c : this.c.getComponents()) {

            ((CardLabel) c).setBorder(null);
        }
        label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
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
}
