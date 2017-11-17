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
            System.out.println("---MENU---");
            System.out.println("[N]OWA GRA");
            System.out.println("[W]YJSCIE\n");
            String odczyt = getUserInput();
            if (odczyt.equals("N")) {
                runGame();
            } else if (odczyt.equals("W"))
                break;
            else
                System.out.println("Wpisano niepoprawną literę");
        }
    }

    private static void runGame() {
        Menu menu = new Menu();
        boolean isTwoPlayersGame = false;
        boolean goBack = false;
        while (true) {
            System.out.println("---NOWA GRA---");
            System.out.println("[J]EDEN GRACZ");
            System.out.println("[D]WÓCH GRACZY");
            System.out.println("[P]OWRÓT\n");
            String odczyt = getUserInput();
            if (odczyt.equals("J")) {
                isTwoPlayersGame=false;
                break;
            } else if (odczyt.equals("D")) {
                isTwoPlayersGame = true;
                break;
            } else if (odczyt.equals("P")) {
                goBack = true;
                break;
            }else {
                System.out.println("Wpisano niepoprawną literę");
            }
        }
        if (!goBack) {
            Character signOfPlayer1;
            Character signOfPlayer2;
            if ((Math.round(Math.random())) % 2 == 0) {
                signOfPlayer1 = 'X';
                signOfPlayer2 = 'O';
            } else {
                signOfPlayer1 = 'O';
                signOfPlayer2 = 'X';
            }
            System.out.println("Podaj imię pierwszego gracza");
            String nameOfPlayer1 = getUserInput();
            Player player1 = new RealPlayer(nameOfPlayer1, signOfPlayer1);
            Player player2;
            if (isTwoPlayersGame) {
                System.out.println("Podaj imię drugiego gracza");
                String nameOfPlayer2 = getUserInput();
                player2 = new RealPlayer(nameOfPlayer2, signOfPlayer2);
            } else {
                player2 = new ComputerPlayer(signOfPlayer2);
            }

            System.out.println("Podaj wielkość planszy");
            int size = menu.obtainInteger();
            TicTacToeGame game = TicTacToeImpl.builder()
                    .gameListeners(menu)
                    .boardDimensions(size, size)
                    .players(player1, player2)
                    .build();
            game.startGame();
        }
    }


    @Override
    public void boardInitialized(Character[][] board) {
        int boardHeight = board.length;
        int boardWidth = board[0].length;
        System.out.print("  ");
        for (int j=0;j<boardWidth;j++) {
            System.out.print(j+1+" ");
        }
        System.out.println("");
        for (int i=0;i<boardHeight;i++) {
            System.out.print(i+1);
            for (int j=0;j<boardWidth;j++) {
                System.out.print(" "+board[i][j]);
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    @Override
    public void infoAboutPlayer(Player player){
        System.out.println("Ruch gracza "+player.getName());
        if (player.getSign()=='X'){
            System.out.println("(gracz X)");
        }
        else{
            System.out.println("(Gracz O)");
        }
    }
    @Override
    public void infoAboutFirstCoordinate(){
        System.out.println("Podaj pierwszą współrzędną");
    }
    @Override
    public void infoAboutSecondCoordinate(){
        System.out.println("Podaj drugą współrzędną");
    }
    @Override
    public void fieldOccupied(int x, int y) {
        System.out.println(String.format("Pole %d , %d jest już zajęte, podaj współrzędne jeszcze raz!",x,y));
    }

    @Override
    public void playerWinAGame(Player player) {
        //TODO: Informacja o wygraniu przez gracza
        System.out.println("Koniec gry\nWygrał gracz "+player.getName()+" (gracz " + player.getSign()+")");
    }
    @Override
    public void infoAboutLacfOfWinner(){
        System.out.println("Cała plansza zajęta. Brak zwycięzcy");
    }
    @Override
    public void infoAboutWrongCoordinate(){
        System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
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
