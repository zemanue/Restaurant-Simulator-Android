package com.example.restaurantsimulator;

import java.util.ArrayList;

public class Restaurant {
    private boolean opened;
    private int openingHour;
    private int currentHour;
    private int closingHour;
    private int day;
    private static ArrayList<Table> tableList;
    private static ArrayList<Table> tableListCapacityOrder;

    public Restaurant() {
        this.opened = true;
        this.openingHour = 13;
        this.currentHour = openingHour;
        this.closingHour = 21;
        this.day = 1;
        this.tableList = new ArrayList<>();
        this.tableListCapacityOrder = new ArrayList<>();
    }

    public Restaurant(int openingHour, int closingHour) {
        this.opened = true;
        this.openingHour = openingHour;
        this.currentHour = openingHour;
        this.closingHour = closingHour;
        this.day = 1;
        this.tableList = new ArrayList<>();
        this.tableListCapacityOrder = new ArrayList<>();
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public int getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
    }

    public int getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(int currentHour) {
        this.currentHour = currentHour;
    }

    public int getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(int closingHour) {
        this.closingHour = closingHour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
