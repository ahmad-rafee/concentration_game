/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.core;

import concentration.game.ui.CardLabel;
import concentration.game.ui.PlayGround;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Suiri Otaku
 */
public class Informer {

    private int id;
    private boolean shown;
    private boolean excluded;
    PlayGround pg;

    public Informer(PlayGround pg) {
        this.pg = pg;
    }

    public Informer(CardLabel card) {
        this.id = card.getId();
        this.excluded = card.isEnabled();
        this.shown = card.isShown();
    }

    public List<Informer> getAll() {
        List<Informer> info = new ArrayList();
        for (CardLabel c : this.pg.getAll()) {
            info.add(new Informer(c));
        }
        return info;
    }
}
