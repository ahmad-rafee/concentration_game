/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.core;

/**
 *
 * @author Suiri Otaku
 */
public class Rank implements MatchStrategy {

    @Override
    public boolean match(Pair pair) {
        return (pair.getCard1().getCard().getRank() == pair.getCard2().getCard().getRank());
    }

}
