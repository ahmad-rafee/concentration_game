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
public class PairFullException extends Exception {

    public PairFullException() {
        super("This pair is full ... try to empty it or create new Pair");
    }
}
