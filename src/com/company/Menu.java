package com.company;

import java.util.Scanner;

public class Menu implements GameEventsListener {
    static Scanner sc = new Scanner(System.in);

    public static String getUserInput() {
        return sc.nextLine();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        while (true) {
            System.out.println("MENU");
            System.out.println("[N]OWA GRA");
            System.out.println("[W]YJSCIE\n");
            String odczyt = getUserInput();
            if (odczyt.equals("N")) {
                uruchomGre();
            } else if (odczyt.equals("W"))
                break;
            else
                System.out.println("Wpisano niepoprawną literę");
        }
    }

    private static void uruchomGre() {
        Menu menu = new Menu();
        System.out.println("Podaj wielkość plannszy");
        int size=menu.obtainInteger();
        TicTacToeGame game = TicTacToeImpl.builder()
                .gameListeners(menu)
                .boardDimensions(size, size)
                .build();
        game.startGame();
    }


    @Override
    public void boardInitialized(Character[][] board) {

    }

    @Override
    public void fieldOccupied(int x, int y) {
        System.out.println("Pole, mozesz podac x  i y jest juz zajte!");
    }

    @Override
    public void playerWinAGame(Player player) {
        //TODO: Informacja o wygraniu przez gracza
        System.out.println("Koniec gry\nWygrał gracz "+player.getSign());
    }

    @Override
    public int obtainInteger(){
        int number=-1;
        boolean isCorrect=false;
        while(!isCorrect) {
            try {
                number = Integer.valueOf(getUserInput());
                isCorrect = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Wyjątek - zły format liczby");
            }
        }
        return number;
    }
}
