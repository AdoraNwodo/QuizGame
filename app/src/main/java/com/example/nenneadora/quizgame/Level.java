package com.example.nenneadora.quizgame;

public enum Level {
    BEGINNER("Easy", 0),
    INTERMEDIATE("Medium", 1),
    EXPERT("Hard", 2);

    private String stringValue;
    private int intValue;
    private Level(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }


}
