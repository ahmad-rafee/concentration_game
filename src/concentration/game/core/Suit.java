/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.core;

import concentration.game.core.Card;
import java.util.ArrayList;

/**
 *
 * @author Suiri Otaku
 */
public class Suit {

    private int power;
    private String color;
    private String suit_name;
    private ArrayList<Card> cards;

    public Suit(int power, String name, String color) {
        this.power = power;
        this.color = color;
        this.suit_name = name;
        this.cards = new ArrayList();
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSuit_name(String suit_name) {
        this.suit_name = suit_name;
    }

    public void addCard(Card card) {
        card.setColor(this.color);
        card.setSuit(this.suit_name);
        card.setSuitrank(this.power);
        this.cards.add(card);
    }

    public int getPower() {
        return power;
    }

    public String getColor() {
        return color;
    }

    public String getSuit_name() {
        return suit_name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

}
