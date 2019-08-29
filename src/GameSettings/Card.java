/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameSettings;

import java.util.Random;
import java.util.stream.IntStream;
import resources.Utils;

/**
 *
 * @author ehite
 */
public class Card {

    private int[] availableNumbers = new int[75];

    private int[][] cardNumbers = new int[5][5];

    private int[] playedNumbers = new int[0];
    

    private int[] cardCorners = new int[4];

    public Card() {
        for (int i = 0; i < 75; i++) {
            availableNumbers[i] = i;
        }
    }

    public void createCard() {
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {
                int n = rand.nextInt(availableNumbers.length);
                int value = this.availableNumbers[n];

                cardNumbers[i][j] = value;

                availableNumbers = Utils.removeInt(availableNumbers, value);

            }

        }

        cardCorners[0] = cardNumbers[0][0];
        cardCorners[1] = cardNumbers[0][4];
        cardCorners[2] = cardNumbers[4][0];
        cardCorners[3] = cardNumbers[4][4];

    }

    public void drawCard() {

        System.out.println("");
        System.out.println("");

        System.out.println("--------------------------");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                String value = "0".concat(Integer.toString(this.cardNumbers[i][j]));

                value = value.substring(value.length() - 2);

                System.out.printf("| %s ", value);

            }
            System.out.print("|");
            System.out.println("");
            System.out.println("--------------------------");

        }

        System.out.println("");
        System.out.println("");
    }

    public void drawCardPlayedNumbers() {

        System.out.println("");
        System.out.println("");

        System.out.println("------------------------------------");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                boolean exists = false;

                for (int k = 0; k < playedNumbers.length; k++) {
                    if (this.cardNumbers[i][j] == playedNumbers[k]) {
                        exists = true;
                    }
                }

                String value = "0".concat(Integer.toString(this.cardNumbers[i][j]));

                value = value.substring(value.length() - 2);

                if (exists) {
                    value = "*".concat(value).concat("*");
                } else {
                    value = " ".concat(value).concat(" ");
                }

                System.out.printf("| %s ", value);

            }
            System.out.print("|");
            System.out.println("");
            System.out.println("------------------------------------");

        }
        System.out.println("Números acertados");
        for (int k = 0; k < playedNumbers.length; k++) {
            System.out.print(playedNumbers[k]);
            System.out.print(", ");
        }

        Utils.addRows(3);

    }

    public void addPlayedNumber(int value) {

       
        
        if (existNumber(value)) {
            playedNumbers = Utils.arrayIntPush(value, playedNumbers);
        }

    }

    public boolean existNumber(int value) {

        boolean exist = false;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cardNumbers[i][j] == value) {
                    exist = true;
                }
            }
        }

        return exist;
    }

    public boolean isFullCard() {
        return playedNumbers.length == 25;
    }
  

    public boolean isFourCorners() {

        /* Needs more values */
        if (playedNumbers.length < 4) {
            return false;
        }

        for (int i = 0; i < cardCorners.length; i++) {

            int value = cardCorners[i];

            boolean contains = IntStream.of(playedNumbers).anyMatch(x -> x == value);

            if (!contains) {
                return false;
            }

        }

        return true;
    }

}
