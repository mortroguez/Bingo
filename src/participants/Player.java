/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participants;

import GameSettings.Card;

/**
 *
 * @author ehite
 */
public class Player {

    public Player() {
        balance = 500;
        wonGames = 0;
        playerCard = new Card();
    }

    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String value) {
        fullName = value;
    }

    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int value) {
        balance = value;
    }

    private int wonGames;

    public int getWonGames() {
        return wonGames;
    }

    public void setWonGames() {
        wonGames++;
    }

    private Card playerCard;

    public Card getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(Card value) {
        playerCard = value;
    }

    public void payBet(int value) {
        this.balance -= value;
    }

    public void setWonMoney(int value) {
        this.balance += value;
    }
}
