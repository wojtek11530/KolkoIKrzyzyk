package com.company;

import java.util.Optional;

public class TicTacToeImpl implements TicTacToeGame {

    private static final char DEFAULT_BOARD_CHARACTER = ' ';

    private GameEventsListener gameEventListener;
    private int boardHeight, boardWidth;

    public Character[][] getGameBoard() {
        return gameBoard;
    }

    private Character[][] gameBoard;

    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private TicTacToeImpl(int boardWidth, int boardHeight, GameEventsListener gameEventsListener, Player player1, Player player2) {
        this.gameEventListener = gameEventsListener;
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
        this.player1 = player1;
        this.player2 = player2;
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
                if (rows[0].equals(player1.getSign())) {
                    return player1;
                } else {
                    return player2;
                }
            } else if (!(columns[0].equals(DEFAULT_BOARD_CHARACTER)) && areColumnsTheSame) {
                if (columns[0].equals(player1.getSign())) {
                    return player1;
                } else {
                    return player2;
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
            if (gameBoard[1][1].equals(player1.getSign())) {
                return player1;
            } else {
                return player2;
            }
        } else {
            return null;
        }
    }

    public int newCoordinate(){
        int coordinate;
        coordinate=gameEventListener.obtainInteger();

        while (coordinate < 1 || coordinate > boardHeight) {
            gameEventListener.infoAboutWrongCoordinate();
            coordinate = gameEventListener.obtainInteger();
        }
        return coordinate;
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


    public void nextTurn(Player player){
        int y = 0;
        int x = 0;
        if (player.getisReal()) {
            gameEventListener.infoAboutPlayer(player);
            gameEventListener.infoAboutFirstCoordinate();
            x = newCoordinate();
            gameEventListener.infoAboutSecondCoordinate();
            y = newCoordinate();
            while (!putSign(player.getSign(), x, y)) {
                gameEventListener.fieldOccupied(x,y);
                gameEventListener.boardInitialized(this.getGameBoard());
                gameEventListener.infoAboutPlayer(player);
                gameEventListener.infoAboutFirstCoordinate();
                x = newCoordinate();
                gameEventListener.infoAboutSecondCoordinate();
                y = newCoordinate();
            }
        }
        else{
            gameEventListener.infoAboutPlayer(player);
            x = 1 + (int)Math.floor(boardHeight*Math.random());
            y = 1 + (int)Math.floor(boardWidth*Math.random());
            while (!putSign(player.getSign(), x, y)) {
                x = 1 + (int)Math.floor(boardHeight*Math.random());
                y = 1 + (int)Math.floor(boardWidth*Math.random());
            }
        }
        if (player.equals(player1)){
            this.currentPlayer= player2;
        }
        else{
            this.currentPlayer= player1;
        }
        gameEventListener.boardInitialized(this.getGameBoard());
    }

    @Override
    public void startGame() {
        if((Math.round(Math.random())) % 2 == 0) {
            currentPlayer = player1;
        }
        else {
            currentPlayer = player2;
        }
        do {
            nextTurn(currentPlayer);
        } while (!findWinner().isPresent() && !isBoardFull());
        if (findWinner().isPresent()){
            gameEventListener.playerWinAGame(this.findWinnerPlayer());
        }
        else {
            gameEventListener.infoAboutLacfOfWinner();
        }
    }

    @Override
    public boolean putSign(Character userSign, int x, int y) {
        //TODO: Tutaj zapisanie wartosci, false jesli jest zle
        if(this.gameBoard[x - 1][y - 1] != DEFAULT_BOARD_CHARACTER) {
            return false;
        }
        else{
            this.gameBoard[x - 1][y - 1] = userSign;
        }
        return true;
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
        private Player player1;
        private Player player2;

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

        public TicTacToeBuilder players(Player player1, Player player2){
            this.player1=player1;
            this.player2=player2;
            return this;
        }

        public TicTacToeImpl build() {
            return new TicTacToeImpl(boardWidth, boardHeight, gameEventListener, player1, player2);
        }
    }
}
