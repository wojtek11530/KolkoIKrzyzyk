package com.company;

import java.util.Optional;

public interface TicTacToeGame {
    boolean userInput(Character userSign, int x, int y);
    Optional<Player> findWinner();
    void startGame();
}
