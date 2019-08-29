/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameSettings;

import java.util.Random;
import java.util.Scanner;
import participants.Computer;
import participants.Human;
import resources.Utils;

/**
 *
 * @author ehite
 */
public class Session {

    final int BET_AMMOUNT = 100;
    final int FOUR_CORNERS = 50;
    final int FULL_CARD = 1000;

    int[] availableNumbers = new int[75];
    private int[] playedNumbersAll = new int[0];

    Human human = new Human();
    Computer computer = new Computer();

    private int totalGames;

    public Session() {

        for (int i = 0; i < 75; i++) {
            availableNumbers[i] = i;
        }

        totalGames = 0;
    }

    public void StartGame() {

        this.getHumanInformation();
        this.showComputerInformation();

        pressEnter();

        this.showMainMenu();

    }

    void showMainMenu() {
        Scanner keyboard = new Scanner(System.in);

        int choice = -1;

        while (choice != 0) {
            System.out.println("*************************");
            System.out.println("**      OPCIONES       **");
            System.out.println("*************************");
            System.out.println("** 1) Jugar partida    **");
            System.out.println("** 2) Ver estado       **");
            System.out.println("** 3) Ver saldo        **");
            System.out.println("*************************");
            System.out.println("** 0) Finalizar        **");
            System.out.println("*************************");
            System.out.printf("%s cuál es opción es ->", Utils.upperCaseWords(this.human.getFullName()));

            choice = Integer.parseInt(keyboard.next());

            if (choice == 0) {

                System.out.println("_________________________________________________");
                System.out.println("                                                 ");
                System.out.println("                          /       /              ");
                System.out.println("----__----__----__----__-/-------/__----------__-");
                System.out.println("  /   ) /   ) /   ) /   /       /   ) /   / /___)");
                System.out.println("_(___/_(___/_(___/_(___/_______(___/_(___/_(___ _");
                System.out.println("   /                                   /        ");
                System.out.println("(_ /                                (_ /         ");

                System.exit(0);
            }

            if (choice == 2) {
                showStatus();
            }

            if (choice == 3) {
                showAccount();
            }

            if (choice == 1) {
                starGame();
            }

        }

    }

    void starGame() {
        
        
        playedNumbersAll = new int[0];
        
        this.totalGames++;

        this.human.getPlayerCard().createCard();
        this.computer.getPlayerCard().createCard();

        this.human.payBet(this.BET_AMMOUNT);
        this.computer.payBet(this.BET_AMMOUNT);

        this.printCards();

        System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
        System.out.println(" Listo para la primer bolita");
        System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
        pressEnter();

        int times = 0;

        while (times < 40) {

            int value = getNumber();

            playedNumbersAll = Utils.arrayIntPush(value, playedNumbersAll);

            Utils.addRows(15);

            System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
            System.out.println(" La bolita favorecida es ".concat(Integer.toString(value)));
            System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
            System.out.printf("Bolitas %s", times + 1);
            GetAllPlayed();

            Utils.addRows(3);

            this.human.getPlayerCard().addPlayedNumber(value);
            this.computer.getPlayerCard().addPlayedNumber(value);

            this.printCardsPlayedNumbers();
            System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
            System.out.println("Gira la tómbola, sale la bolita...");
            System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");

            pressEnter();

            if ((this.human.getPlayerCard().isFullCard()) || (this.human.getPlayerCard().isFullCard())) {
                Utils.addRows(15);

                System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
                System.out.println("FELICIDADES HAY UN GANADOR");
                System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");

                break;

            } else {
                Utils.addRows(15);
            }

            times++;
        }

        Utils.addRows(15);

        if (this.human.getPlayerCard().isFullCard()) {
            System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
            System.out.printf("Felicidades %s", this.human.getFullName());
            System.out.println("");
            System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");

            this.human.setWonGames();
            this.human.setWonMoney(FULL_CARD);
        } else {

            if (this.human.getPlayerCard().isFourCorners()) {
                System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
                System.out.printf("%s ganaste cuatro esquinas, peor es nada!.", this.human.getFullName());
                System.out.println("");
                System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
                this.human.setWonMoney(FOUR_CORNERS);
            } else {
                System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
                System.out.printf("Ánimo %s sigue jugando.", this.human.getFullName());
                System.out.println("");
                System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
            }

        }

        if (this.computer.getPlayerCard().isFullCard()) {
            System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
            System.out.printf("Felicidades %s", this.computer.getFullName());
            System.out.println("");
            System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");

            this.computer.setWonGames();
            this.computer.setWonMoney(FULL_CARD);
        } else {

            if (this.computer.getPlayerCard().isFourCorners()) {
                System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
                System.out.printf("%s ganaste cuatro esquinas, peor es nada!.", this.computer.getFullName());
                System.out.println("");
                System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
                this.computer.setWonMoney(FOUR_CORNERS);
            } else {
                System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
                System.out.printf("Ánimo %s sigue jugando.", this.computer.getFullName());
                System.out.println("");
                System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
            }

        }

        pressEnter();
        Utils.addRows(15);

    }

    int getNumber() {

        Random rand = new Random();

        int n = rand.nextInt(availableNumbers.length);
        int value = this.availableNumbers[n];

        availableNumbers = Utils.removeInt(availableNumbers, value);

        return value;

    }

    void printCards() {

        System.out.println("");
        System.out.printf("Cartón de %s", this.human.getFullName());

        this.human.getPlayerCard().drawCard();

        System.out.println("");
        System.out.printf("Cartón de %s", this.computer.getFullName());

        this.computer.getPlayerCard().drawCard();
        this.pressEnter();

    }

    void printCardsPlayedNumbers() {

        System.out.println("");
        System.out.printf("Cartón de %s", this.human.getFullName());

        this.human.getPlayerCard().drawCardPlayedNumbers();

        System.out.println("");
        System.out.printf("Cartón de %s", this.computer.getFullName());

        this.computer.getPlayerCard().drawCardPlayedNumbers();

    }

    void getHumanInformation() {

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Favor ingresar su nombre completo: ");
        human.setFullName(keyboard.next());

        boolean validated = false;

        while (!validated) {
            System.out.print("Favor ingresar edad: ");

            int ageTemp = Integer.parseInt(keyboard.next());

            if (validateAge(ageTemp)) {
                human.setAge(ageTemp);

                validated = true;
            } else {
                System.out.println("*************************");
                System.out.println("**  Edad no permitida  **");
                System.out.println("*************************");
            }
        }
        System.out.println("");
        System.out.println("░░░░░░░░░░░░░░░░");
        System.out.println(" Bienvendido(a) y suerte!");
        System.out.println("░░░░░░░░░░░░░░░░");
        System.out.printf("Su saldo actual es %d.\n", human.getBalance());
        System.out.println("*************************");

    }

    boolean validateAge(int age) {

        return age >= 18;

    }

    void showComputerInformation() {
        computer.setFullName("Servidor principal");
        computer.setOperationSystem("Windows 10");
        computer.setMemorySize(16);

        System.out.println("");
        System.out.println("░░░░░░░░░░░░░░░░");
        System.out.println(" Su contrincante");
        System.out.println("░░░░░░░░░░░░░░░░");
        System.out.printf(" Nombre            : %s.\n", computer.getFullName());
        System.out.printf(" Sistema operativo : %s.\n", computer.getOperationSystem());
        System.out.printf(" Memoria GBs       : %s.\n", computer.getMemorySize());
        System.out.printf(" Saldo actual      : %s.\n", computer.getBalance());
        System.out.println("*************************");
    }

    void showStatus() {

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("_________________________________________________");
        System.out.println("          ESTADO ACTUAL DEL JUEGO            ");
        System.out.println("_________________________________________________");
        System.out.printf("Partidas jugadas %d", this.totalGames);
        System.out.println("");
        System.out.printf("Partidas ganadas por %s %d", this.human.getFullName(), this.human.getWonGames());
        System.out.println("");
        System.out.printf("Partidas ganadas por %s %d", this.computer.getFullName(), this.computer.getWonGames());
        System.out.println("");
        System.out.println("_________________________________________________");
        System.out.println("");

        this.pressEnter();
    }

    void showAccount() {

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("_________________________________________________");
        System.out.println("              ESTADO DE CUENTA            ");
        System.out.println("_________________________________________________");
        System.out.printf("Saldo %s: %d", this.human.getFullName(), this.human.getBalance());
        System.out.println("");
        System.out.printf("Saldo %s: %d", this.computer.getFullName(), this.computer.getBalance());
        System.out.println("");
        System.out.println("_________________________________________________");
        System.out.println("");

        this.pressEnter();
    }

    void pressEnter() {

        System.out.println("Presione enter para continuar ☺");

        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public void GetAllPlayed() {

        Utils.addRows(2);

        for (int i = 0; i < playedNumbersAll.length; i++) {
            System.out.printf("%d ,", playedNumbersAll[i]);
        }
    }

}
