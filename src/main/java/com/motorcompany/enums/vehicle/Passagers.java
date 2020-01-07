package com.motorcompany.enums.vehicle;

public enum Passagers {


    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4);
    private final int value;

    Passagers(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}