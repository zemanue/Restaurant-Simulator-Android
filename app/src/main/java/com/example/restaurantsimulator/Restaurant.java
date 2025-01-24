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

    public static int findSuitableTable(int people) {
        ArrayList<Customer> customerGroup = new ArrayList<>();
        // Create the customers and check if they prefer the window
        int customerPrefersWindow = 0;
        for (int i = 0; i < people; i++) {
            Customer customer = new Customer();
            customerGroup.add(customer);
            if (customer.getPrefersWindow()) {
                customerPrefersWindow++;
            }
        }
        // Try to assign a table next to the window (if half of the customers or more
        // prefer window)
        if (customerPrefersWindow >= Math.ceil(people / 2)) {
//            statistics.incrementWindowPreference();
            System.out.println("Los clientes prefieren una mesa junto a la ventana. Buscando...");
            for (Table table : tableListCapacityOrder) {
                if (table.isAvailable()
                        && table.getMaxCapacity() >= people
                        && table.getMaxCapacity() <= people + 2
                        && table.isNextToWindow()) {

                    System.out.println("Mesa junto a la ventana encontrada: ");
//                    statistics.incrementTimesWindowPreferenceGiven();
                    table.occupyTable(customerGroup);
//                    statistics.incrementOccupiedTables();
//                    statistics.addCustomer(people);
//                    statistics.addEarnings(table.getTotalSpending());
//                    statistics.addTips(table.getTotalTips());
                    return table.getTableNumber();
                }
            }
            System.out.println("No hay mesas disponibles junto a la ventana. Se buscará una libre.");
//            statistics.incrementTimesWithoutTable();
        } else {
            System.out.println("A los clientes no les importa dónde sentarse. Buscando la primera disponible...");
        }

        // If it doesn't work, a regular table will try to be assigned
        for (Table table : tableListCapacityOrder) {
            if (table.isAvailable()
                    && table.getMaxCapacity() >= people
                    && table.getMaxCapacity() <= people + 2) {
                System.out.println("Mesa encontrada: ");
                table.occupyTable(customerGroup);
//                statistics.incrementOccupiedTables();
//                statistics.addCustomer(people);
//                statistics.addEarnings(table.getTotalSpending());
//                statistics.addTips(table.getTotalTips());
                return table.getTableNumber();
            }
        }
        System.out.println("'Lo siento, no quedan mesas disponibles para " + people + " personas. Vuelvan más tarde.'");
        System.out.println();
        return 0;
    }


}
