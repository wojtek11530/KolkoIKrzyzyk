package com.company;

import java.util.Optional;

public interface TicTacToeGame {

    Optional<Player> findWinner();
    void startGame();
}
