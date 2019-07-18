/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.core;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Suiri Otaku
 */
public class Card {

    private int rank;
    private int suitrank;
// 0 -ace - 2 to 10 , 11 jack, 12 queen , 13 king

    private String suit;
    private String color;
    private ImageIcon image;
    

    public Card(int rank, ImageIcon image) {
        this.image = image;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public int getSuitrank() {
        return suitrank;
    }

    public String getSuit() {
        return suit;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void scaleIcon(int width, int height) {

        this.image = new ImageIcon(this.image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

    }

    public String getColor() {
        return color;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setSuitrank(int suitrank) {
        this.suitrank = suitrank;
    }

}
