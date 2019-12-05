package com.motorcompany.enums.vehicle;

public enum Whells {

    TWO(2),
    THREE(3),
    FOUR(4);
    private final int value;

    private Whells(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}