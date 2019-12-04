package com.motorcompany.config.enums;

public enum VehicleWheels {

    TWO(2),
    THREE(3),
    FOUR(4);
    private final int value;

    private VehicleWheels(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}