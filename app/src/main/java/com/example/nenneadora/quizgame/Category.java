package com.example.nenneadora.quizgame;


public enum Category {
    GENERAL("General", 0),
    ZOBIAWA("Zobiawa",1);


    private String stringValue;
    private int intValue;
    private Category(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
