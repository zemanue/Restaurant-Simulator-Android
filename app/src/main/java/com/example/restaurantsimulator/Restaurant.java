package com.example.restaurantsimulator;

import java.util.ArrayList;
import java.util.Collections;

public class Restaurant {
    private boolean opened;
    private int openingHour;
    private int currentHour;
    private int closingHour;
    private int day;
    private int numTables;
    private static ArrayList<Table> tableList;
    private static ArrayList<Table> tableListCapacityOrder;

    public Restaurant() {
        this.opened = true;
        this.openingHour = 13;
        this.currentHour = openingHour;
        this.closingHour = 21;
        this.day = 1;
        this.numTables = 16;
        this.tableList = new ArrayList<>();
        this.tableListCapacityOrder = new ArrayList<>();
    }

    public Restaurant(int openingHour, int closingHour) {
        this.opened = true;
        this.openingHour = openingHour;
        this.currentHour = openingHour;
        this.closingHour = closingHour;
        this.day = 1;
        this.numTables = 16;
        this.tableList = new ArrayList<>(numTables);
        this.tableListCapacityOrder = new ArrayList<>(numTables);
    }

    public Restaurant(int openingHour, int closingHour, int numTables) {
        this.opened = true;
        this.openingHour = openingHour;
        this.currentHour = openingHour;
        this.closingHour = closingHour;
        this.day = 1;
        this.numTables = numTables;
        this.tableList = new ArrayList<>(numTables);
        this.tableListCapacityOrder = new ArrayList<>(numTables);
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

    public int getNumTables() {
        return numTables;
    }
    public void setNumTables(int numTables) {
        this.numTables = numTables;
    }

    public static ArrayList<Table> getTableList() {
        return tableList;
    }
    public static void setTableList(ArrayList<Table> tableList) {
        Restaurant.tableList = tableList;
    }

    public static ArrayList<Table> getTableListCapacityOrder() {
        return tableListCapacityOrder;
    }
    public static void setTableListCapacityOrder(ArrayList<Table> tableListCapacityOrder) {
        Restaurant.tableListCapacityOrder = tableListCapacityOrder;
    }

    public static void initializeTables(int[] capacities, boolean[] nextToWindow) {
        for (int i = 0; i < capacities.length; i++) {
            tableList.add(new Table(i + 1, capacities[i], nextToWindow[i]));
        }
        tableListCapacityOrder = new ArrayList<>(tableList);
        Collections.sort(tableListCapacityOrder);
    }

}
