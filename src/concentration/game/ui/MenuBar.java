/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.ui;

import concentration.game.core.Color_Rank;
import concentration.game.core.Rank;
import java.util.Comparator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Suiri Otaku
 */
public class MenuBar extends JMenuBar {

    JMenu view, strs, sort;
    JMenuItem ch_cover, suit_sort, rank_sort, color_st, rank_st;
    PlayGround pg;
    ScorePanel sp;
    List<ImageIcon> covers;

    public MenuBar(PlayGround pg, ScorePanel sp) {
        init();
        this.pg = pg;
        this.sp = sp;
    }

    private void init() {
        view = new JMenu("view");
        ch_cover = new JMenuItem("Change Cover");
        ch_cover.addActionListener(e -> {
            Cover_Chooser cs = new Cover_Chooser(pg, covers, sp);

        });

        suit_sort = new JMenuItem("Sort All Cards") {

            {
                addActionListener(e -> {
                    pg.setComparatorIndex(0);
                    pg.sortAll();
                });

            }
        };

        strs = new JMenu("Matching Strategies");
        color_st = new JMenuItem("Matching Color and Rank") {
            {

                addActionListener(e -> {
                    pg.game.setStrategy(new Color_Rank());

                });
            }

        };
        rank_st = new JMenuItem("Matching Rank") {
            {
                addActionListener(e -> {

                    pg.game.setStrategy(new Rank());

                });
            }

        };

        view.add(ch_cover);
        view.add(suit_sort);
        strs.add(color_st);
        strs.add(rank_st);
        add(view);
        add(strs);
    }

    public void setCovers(List<ImageIcon> covers) {
        this.covers = covers;
    }

}
