package com.amacom.amacom.model;

public enum EAffectationDegree {
    ONE(2), TWO(3), THREE(4), FOUR(5), FIVE(6), SIX(7), SEVEN(8), EIGHT(9),
    NINE(10), TEN(11);

    EAffectationDegree(int value) {
        this.value = value;
    }

    private int value;

    public String toString() {
        return Integer.toString(value);
    }

    public String getCode() {
        return Integer.toString(value);
    }
}