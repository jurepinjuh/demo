package com.jurepinjuh.demo.Models;

public enum ERole {
    ADMIN(1),
    USER(2);

    public final int value;

    private ERole(int value) {
        this.value = value;
    }
}
