package com.motorcompany.config.enums;

public enum VehiclePassagens {


    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4);
    private final int value;

    private VehiclePassagens(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}