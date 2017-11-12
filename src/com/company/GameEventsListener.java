package com.company;

public interface GameEventsListener {
    void boardInitialized(Character[][] board);
    void fieldOccupied(int x, int y);
    void playerWinAGame(Player player);

    int obtainInteger();
}
