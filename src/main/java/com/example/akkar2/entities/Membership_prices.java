package com.example.akkar2.entities;

public enum Membership_prices {
    FiveAnnouncement(500),
    TenAnnouncement(1000);


    private final int value;

    private Membership_prices(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
