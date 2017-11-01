package com.company;

public enum Player {
    CIRCLE('O'),
    CROSS('X');

    Player(Character sign) {
        this.sign = sign;
    }

    private final Character sign;

    public Character getSign() {
        return sign;
    }
}
