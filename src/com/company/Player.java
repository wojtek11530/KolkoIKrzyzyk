package com.company;

abstract class Player{
    private final String name;
    private final Character Sign;
    private final boolean isReal;

    public boolean getisReal() {
        return isReal;
    }

    public String getName() {
        return name;
    }

    public Character getSign() {
        return Sign;
    }

    public Player(String name, Character Sign, boolean isReal){
        this.name=name;
        this.Sign = Sign;
        this.isReal = isReal;
    }
}