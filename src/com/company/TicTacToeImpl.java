package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TicTacToeImpl implements TicTacToeGame {

    private static final char DEFAULT_BOARD_CHARACTER = ' ';

    private GameEventsListener gameEventListener;
    private int boardHeight, boardWidth;

    public Character[][] getGameBoard() {
        return gameBoard;
    }

    private Character[][] gameBoard;

    private Player playerX = Player.CROSS;
    private Player playerO = Player.CIRCLE;

    private TicTacToeImpl(int boardWidth, int boardHeight, GameEventsListener gameEventsListener) {
        this.gameEventListener = gameEventsListener;
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
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


    public void putX(int x, int y) {
        if (this.gameBoard[x - 1][y - 1] != ' ') {
            System.out.println("Pole zajęte");
        } else {
            this.gameBoard[x - 1][y - 1] = 'X';
        }
    }

    public void putO(int x, int y) {
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
            Character [] rows = new Character[boardWidth];
            Character [] columns = new Character[boardHeight];
            for (int j=0; j<boardWidth; j++){
                rows[j] = this.getGameBoard()[i][j];
            }
            for (int j=0; j<boardHeight; j++){
                columns[j] = this.getGameBoard()[j][i];
            }
            boolean areRowsTheSame=true;
            boolean areColumnsTheSame=true;

            for (int j=0; j<boardWidth; j++){
                if(rows[0] != rows[j]){
                    areRowsTheSame=false;
                }
            }

            for (int j=0; j<boardHeight; j++){
                if(columns[0] != columns[j]){
                    areColumnsTheSame=false;
                }
            }
            if (!(rows[0].equals(DEFAULT_BOARD_CHARACTER)) && areRowsTheSame) {
                if (rows[0].equals(playerX.getSign())) {
                    return playerX;
                } else {
                    return playerO;
                }
            } else if (!(columns[0].equals(DEFAULT_BOARD_CHARACTER)) && areColumnsTheSame) {
                if (columns[0].equals(playerX.getSign())) {
                    return playerX;
                } else {
                    return playerO;
                }
            }
        }

        boolean isDiagonalOneTheSame=true;
        boolean isDiagonalTwoTheSame=true;

        for (int j=0; j<boardWidth; j++){
            if(gameBoard[1][1] != gameBoard[j][j]){
                isDiagonalOneTheSame=false;
            }
        }

        for (int j=0; j<boardHeight; j++){
            if(gameBoard[1][1] != gameBoard[j][boardHeight-1-j]){
                isDiagonalTwoTheSame=false;
            }
        }
        if ((!gameBoard[0][0].equals(DEFAULT_BOARD_CHARACTER) &&
                isDiagonalOneTheSame) || (
                !gameBoard[0][2].equals(DEFAULT_BOARD_CHARACTER) &&
                        isDiagonalTwoTheSame)) {
            if (gameBoard[1][1].equals(playerX.getSign())) {
                return playerX;
            } else {
                return playerO;
            }
        } else {
            return null;
        }
    }

    public int newCoordinate(){
        int coordinate;
        coordinate=gameEventListener.obtainInteger();

        while (coordinate < 1 || coordinate > boardHeight) {
            System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
            coordinate = gameEventListener.obtainInteger();
        }
        return coordinate;
    }

    public void printTheBoard(){
        System.out.print("  ");
        for (int j=0;j<boardWidth;j++) {
            System.out.print(j+1+" ");
        }
        System.out.println("");
        for (int i=0;i<boardHeight;i++) {
            System.out.print(i+1);
            for (int j=0;j<boardWidth;j++) {
                System.out.print(" "+this.getGameBoard()[i][j]);
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    public boolean isBoardFull(){
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == DEFAULT_BOARD_CHARACTER) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void startGame() {
        float whichPlayer = (Math.round(Math.random()));

        do {
            int y = 0;
            int x = 0;
            if (whichPlayer % 2 == 0) {
                printTheBoard();
                System.out.println("Gracz X");
                System.out.println("Podaj pierwszą współrzędną");
                x = newCoordinate();
                System.out.println("Podaj drugą współrzędną");
                y = newCoordinate();
                while (!gameBoard[x - 1][y - 1].equals(DEFAULT_BOARD_CHARACTER)) {
                    System.out.println("Pole jest już zajęte, podaj współrzędne jeszcze raz");
                    System.out.println("Gracz X");
                    System.out.println("Podaj pierwszą współrzędną");
                    x = newCoordinate();
                    System.out.println("Podaj drugą współrzędną");
                    y = newCoordinate();
                }
                this.putX(x, y);
                whichPlayer++;
            }
            else {
                printTheBoard();
                System.out.println("Gracz O");
                System.out.println("Podaj pierwszą współrzędną");
                x = newCoordinate();
                System.out.println("Podaj drugą współrzędną");
                y = newCoordinate();
                while (!gameBoard[x - 1][y - 1].equals(DEFAULT_BOARD_CHARACTER)) {
                    System.out.println("Pole jest już zajęte, podaj współrzędne jeszcze raz");
                    System.out.println("Gracz O");
                    System.out.println("Podaj pierwszą współrzędną");
                    x = newCoordinate();
                    System.out.println("Podaj drugą współrzędną");
                    y = newCoordinate();
                }
                this.putO(x, y);
                whichPlayer--;

            }



        } while (!findWinner().isPresent() && !isBoardFull());
        if (findWinner().isPresent()){
            gameEventListener.playerWinAGame(this.findWinnerPlayer());
        }
        else {
            System.out.println("Cała plansza jest zajęta. Brak zwycięzcy");
        }
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
            boardWidth = width >= MIN_BOARD_WIDTH ? width : DEFAULT_BOARD_WIDTH;
            boardHeight = height >= MIN_BOARD_HEIGHT ? height : DEFAULT_BOARD_HEIGHT;
            return this;
        }

        public TicTacToeImpl build() {
            return new TicTacToeImpl(boardWidth, boardHeight, gameEventListener);
        }
    }
}
