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
public class Player {

    int score, tries, moves;

    public Player() {
        this.score = -270;
        this.tries = 0;
        this.moves = 0;
    }

    public int getScore() {
        return score;
    }

    public int getTries() {
        return tries;
    }

    public int getMoves() {
        return moves;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void decrementTries() {
        this.tries--;
    }

}
