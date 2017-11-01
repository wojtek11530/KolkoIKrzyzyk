package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TicTacToeImpl implements TicTacToeGame {

    private static final char DEFAULT_BOARD_CHARACTER = ' ';

    private GameEventsListener gameEventListener;
    private int boardHeight, boardWidth;
    private Character[][] gameBoard;

    private Player playerX = Player.CROSS;
    private Player playerO = Player.CIRCLE;

    private TicTacToeImpl(int boardWidth, int boardHeight, GameEventsListener gameEventsListener) {
        this.gameEventListener = gameEventsListener;
        initGameBoard(boardWidth, boardHeight);
    }

    private void initGameBoard(int boardWidth, int boardHeight) {
        gameBoard = new Character[boardWidth][boardHeight];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = DEFAULT_BOARD_CHARACTER;
            }
        }

        if(hasEventListener()){
            gameEventListener.boardInitialized(gameBoard);
        }
    }

    private boolean hasEventListener() {
        return gameEventListener != null;
    }


    public void wstawX(int x, int y) {
        if (this.gameBoard[x - 1][y - 1] != ' ') {
            System.out.println("Pole zajęte");
        } else {
            this.gameBoard[x - 1][y - 1] = 'X';
        }
    }

    public void wstawO(int x, int y) {
        if (this.gameBoard[x - 1][y - 1] != ' ') {
            System.out.println("Pole zajęte");
        } else {
            this.gameBoard[x - 1][y - 1] = 'O';
        }
    }

    @Override
    public Optional<Player> findWinner() {
        return Optional.ofNullable(findWinnerPlayer());
    }

    private Player findWinnerPlayer() {
        //Tutaj trzeba jeszcze prziwidziec inna boradHeight
        for (int i = 0; i < boardWidth; i++) {
            Character wiersz1 = gameBoard[i][0];
            Character wiersz2 = gameBoard[i][1];
            Character wiersz3 = gameBoard[i][2];

            Character kolumna1 = gameBoard[0][i];
            Character kolumna2 = gameBoard[1][i];
            Character kolumna3 = gameBoard[2][i];

            if (!(wiersz1.equals(DEFAULT_BOARD_CHARACTER)) && wiersz1 == wiersz2 && wiersz1 == wiersz3) {
                if (wiersz1.equals(playerX.getSign())) {
                    return playerX;
                } else {
                    return playerO;
                }
            } else if (!(kolumna1.equals(DEFAULT_BOARD_CHARACTER)) && kolumna1 == kolumna2 && kolumna1 == kolumna3) {
                if (kolumna1.equals(playerX.getSign())) {
                    return playerX;
                } else {
                    return playerO;
                }
            }
        }

        if ((!gameBoard[0][0].equals(DEFAULT_BOARD_CHARACTER) &&
                gameBoard[0][0] == gameBoard[1][1] &&
                gameBoard[0][0] == gameBoard[2][2]) || (
                !gameBoard[0][2].equals(DEFAULT_BOARD_CHARACTER) &&
                        gameBoard[0][2] == gameBoard[1][1] &&
                        gameBoard[0][2] == gameBoard[2][0])) {
            if (gameBoard[1][1].equals(playerX.getSign())) {
                return playerX;
            } else {
                return playerO;
            }
        } else
            return null;
    }


    public void partia() {

        float i = (Math.round(Math.random()));

        do {
            int y = 0;
            int x = 0;
            if (i % 2 == 0) {

                System.out.println("Gracz X");
                System.out.println("Podaj pierwszą współrzędną");
                x = zwrocWspolrzedna();
                while (!(x == 1 || x == 2 || x == 3)) {
                    System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                    x = zwrocWspolrzedna();
                }
                System.out.println("Podaj drugą współrzędną");
                y = zwrocWspolrzedna();
                while (!(y == 1 || y == 2 || y == 3)) {
                    System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                    y = zwrocWspolrzedna();
                }
                while (!gameBoard[x - 1][y - 1].equals(DEFAULT_BOARD_CHARACTER)) {
                    System.out.println("Pole jest już zajęte, podaj współrzędne jeszcze raz");
                    System.out.println("Gracz X");
                    System.out.println("Podaj pierwszą współrzędną");
                    x = zwrocWspolrzedna();
                    while (!(x == 1 || x == 2 || x == 3)) {
                        System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                        x = zwrocWspolrzedna();
                    }
                    System.out.println("Podaj drugą współrzędną");
                    y = zwrocWspolrzedna();
                    while (!(y == 1 || y == 2 || y == 3)) {
                        System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                        y = zwrocWspolrzedna();
                    }
                }
                this.wstawX(x, y);
            } else {

                System.out.println("Gracz O");
                System.out.println("Podaj pierwszą współrzędną");
                x = zwrocWspolrzedna();
                while (!(x == 1 || x == 2 || x == 3)) {
                    System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                    x = zwrocWspolrzedna();
                }
                System.out.println("Podaj drugą współrzędną");
                y = zwrocWspolrzedna();
                while (!(y == 1 || y == 2 || y == 3)) {
                    System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                    y = zwrocWspolrzedna();
                }

                while (!gameBoard[x - 1][y - 1].equals(DEFAULT_BOARD_CHARACTER)) {
                    System.out.println("Pole jest już zajęte, podaj współrzędne jeszcze raz");
                    System.out.println("Gracz O");
                    System.out.println("Podaj pierwszą współrzędną");
                    x = zwrocWspolrzedna();
                    while (!(x == 1 || x == 2 || x == 3)) {
                        System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                        x = zwrocWspolrzedna();
                    }
                    System.out.println("Podaj drugą współrzędną");
                    y = zwrocWspolrzedna();
                    while (!(y == 1 || y == 2 || y == 3)) {
                        System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                        y = zwrocWspolrzedna();
                    }
                }
                this.wstawO(x, y);
            }
            i++;

        } while (!findWinner().isPresent());
    }


    @Override
    public boolean userInput(Character userSign, int x, int y) {
        //TODO: Tutaj zapisanie wartosci, false jesli jest zle
        if(jakisZlyWArynek) {
            gameEventListener.fieldOccupied(x,y);
            return false;
        }

        return true;
    }

    @Override
    public void startGame() {

    }

    public static TicTacToeBuilder builder() {
        return new TicTacToeBuilder();
    }


    static class TicTacToeBuilder {

        private static final int MIN_BOARD_WIDTH = 3;
        private static final int MIN_BOARD_HEIGHT = 3;

        private static final int DEFAULT_BOARD_WIDTH = 5;
        private static final int DEFAULT_BOARD_HEIGHT = 5;

        private int boardHeight, boardWidth;
        private GameEventsListener gameEventListener;

        private TicTacToeBuilder() {
            boardHeight = DEFAULT_BOARD_HEIGHT;
            boardWidth = DEFAULT_BOARD_WIDTH;
        }

        public TicTacToeBuilder gameListeners(GameEventsListener gameEventsListener) {
            this.gameEventListener = gameEventsListener;
            return this;
        }

        public TicTacToeBuilder boardDimensions(int width, int height) {
            boardWidth = width > MIN_BOARD_WIDTH ? width : DEFAULT_BOARD_WIDTH;
            boardHeight = height > MIN_BOARD_HEIGHT ? height : DEFAULT_BOARD_HEIGHT;
            return this;
        }

        public TicTacToeImpl build() {
            return new TicTacToeImpl(boardWidth, boardHeight, gameEventListener);
        }
    }
}
