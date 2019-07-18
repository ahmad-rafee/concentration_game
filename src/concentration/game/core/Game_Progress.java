/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.core;

import concentration.game.ui.PlayGround;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Suiri Otaku
 */
/// For multi player progress send
public class Game_Progress {

    Boolean[] status = new Boolean[1];
    Player pl; // send player object [score,turn]
    List<Informer> informer = new ArrayList(); // send list indecies
    int selectedId; // send selected card by the player
    int[] pair = new int[1];// send pair status to\back player2

    public Game_Progress(PlayGround pg) {
        this.status = pg.getGame().status;
        this.pl = pg.getGame().pl;
        getSelectedCardId(pg.getGame());
        this.informer = (new Informer(pg)).getAll();
    }

    private void getSelectedCardId(Game game) {
        this.selectedId = game.card_pair.getCard1().getId();
    }
}
