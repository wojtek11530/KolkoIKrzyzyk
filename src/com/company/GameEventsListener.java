package com.company;

public interface GameEventsListener {
    void boardInitialized(Character[][] board);
    void fieldOccupied(int x, int y);
    void playerWinAGame(Player player);
    void infoAboutPlayer(Player player);
    void infoAboutFirstCoordinate();
    void infoAboutSecondCoordinate();
    void infoAboutLacfOfWinner();
    void infoAboutWrongCoordinate();
    int obtainInteger();
}
