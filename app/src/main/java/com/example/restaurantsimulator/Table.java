package com.example.restaurantsimulator;

import java.util.Random;
import java.util.ArrayList;

public class Table implements Comparable<Table> {
    Random random = new Random();
    private int tableNumber;
    private boolean nextToWindow;
    private boolean available;
    private int maxCapacity;
    private Customer[] peopleSeated;
    private int occupiedSeats;
    private int tableSatisfaction = 0;
    private static int sumOfTablesSatisfaction = 0;
    private int timeOccupied = 0;
    private double totalSpending = 0;
    private double totalTips = 0;

    // GETTERS AND SETTERS
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isNextToWindow() {
        return nextToWindow;
    }

    public void setNextToWindow(boolean nextToTheWindow) {
        this.nextToWindow = nextToTheWindow;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Customer[] getPeopleSeated() {
        return peopleSeated;
    }

    public void setPeopleSeated(Customer[] peopleSeated) {
        this.peopleSeated = peopleSeated;
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(int occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public static int getSumOfTablesSatisfaction() {
        return sumOfTablesSatisfaction;
    }

    public static void setSumOfTablesSatisfaction(int totalSatisfaction) {
        Table.sumOfTablesSatisfaction = totalSatisfaction;
    }

    public int getTimeOccupied() {
        return timeOccupied;
    }

    public void setTimeOccupied(int timeOccupied) {
        this.timeOccupied = timeOccupied;
    }

    public double getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }

    public double getTotalTips() {
        return totalTips;
    }

    public void setTotalTips(double totalTips) {
        this.totalTips = totalTips;
    }

    // Constructor with maxCapacity by default = 2;
    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.nextToWindow = false;
        this.available = true;
        this.maxCapacity = 2;
        this.peopleSeated = new Customer[this.maxCapacity];
        this.occupiedSeats = 0;
    }

    public Table(int tableNumber, int maxCapacity) {
        this.tableNumber = tableNumber;
        this.nextToWindow = false;
        this.maxCapacity = maxCapacity;
        this.available = true;
        this.peopleSeated = new Customer[maxCapacity];
        this.occupiedSeats = 0;
    }

    public Table(int tableNumber, int maxCapacity, boolean nextToTheWindow) {
        this.tableNumber = tableNumber;
        this.nextToWindow = nextToTheWindow;
        this.maxCapacity = maxCapacity;
        this.available = true;
        this.peopleSeated = new Customer[maxCapacity];
        this.occupiedSeats = 0;
    }

    @Override
    public String toString() {
        return "Mesa " + tableNumber;
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mesa " + tableNumber);
        sb.append((available) ? ", disponible" : ", ocupada por: " + occupiedSeats + " personas");
        sb.append(". Capacidad máxima: " + maxCapacity);
        sb.append((nextToWindow) ? ", Ventana" : ", No ventana");
        return sb.toString();
    }

    public boolean occupyTable(ArrayList<Customer> customerGroup) {
        if (customerGroup.size() <= 0) {
            System.out.println("Error: El número de personas debe ser mayor a 0");
            return false;
        } else if (customerGroup.size() > maxCapacity) {
            System.out.println(this.toString() + " no adjudicada: Esta mesa tiene capacidad máxima para "
                    + maxCapacity + " personas.");
            return false;
        } else {
            this.available = true;
            this.occupiedSeats = customerGroup.size();
            this.peopleSeated = customerGroup.toArray(new Customer[customerGroup.size()]);
            this.timeOccupied = 0;

            for (Customer customer : customerGroup) {
                int satisfaction = calculateInitialSatisfaction(customer, customerGroup.size());
                customer.setSatisfactionLevel(satisfaction);
                totalSpending += customer.calculateSpending();
                totalTips += customer.calculateTip();
            }

            System.out.println(this.getDetails());
            System.out.println("");
            return true;
        }
    }

    public int calculateInitialSatisfaction(Customer customer, int groupSize) {
        int satisfaction = 5;

        // If the customer prefers window and the group is seated next to the window, +1
        // or -1
        if (customer.getPrefersWindow()) {
            if (this.nextToWindow) {
                satisfaction += 1;
            } else {
                satisfaction -= 1;
            }
        }

        // If the table is too big or too small, -1
        if (this.getMaxCapacity() - groupSize > 2 || this.getMaxCapacity() - groupSize == 0) {
            satisfaction -= 1;
        }

        // Satisfaction can't be bigger than 5 or less than 1
        if (satisfaction > 5) {
            satisfaction = 5;
        } else if (satisfaction < 1) {
            satisfaction = 1;
        }

        return satisfaction;
    }

    public void incrementTimeOccupied() {
        if (available) {
            timeOccupied++;
            if (timeOccupied >= random.nextInt(4) + 1) {
                freeTable(true);
            } else {
                System.out.println(
                        "La mesa " + tableNumber + " sigue sentada. Lleva " + timeOccupied + " hora/s sentada.");
            }
        }
    }

    public void freeTable(boolean message) {
        if (!available) {
            System.out.println("La mesa " + tableNumber + " ya está libre.");
            return;
        }
        if (message) {
            System.out.println("Mesa " + tableNumber + " liberada.");
        }
        for (int i = 0; i < occupiedSeats; i++) {
            int satisfaction = peopleSeated[i].getSatisfactionLevel();
            tableSatisfaction += satisfaction;
            sumOfTablesSatisfaction += satisfaction;
            if (message) {
                System.out.println(
                        "- Cliente " + (i + 1) + " dejó la mesa con una satisfacción de " + satisfaction + "/5");
            }
            peopleSeated[i] = null;
        }
        if (message && available) {
            System.out.println("- Satisfacción media: " + calculateAverageSatisfaction() + "/5");
            System.out.println("- Total gastado por el grupo: " + totalSpending + " euros");
            double tipPercentage = Math.round((totalTips / totalSpending) * 1000.0) / 10.0;
            System.out.println("- Propina recibida: " + totalTips + " euros (" + tipPercentage + "% del total)");
        }
        this.available = false;
        this.occupiedSeats = 0;
        this.timeOccupied = 0;
        this.tableSatisfaction = 0;
    }

    public double calculateAverageSatisfaction() {
        if (occupiedSeats > 0) {
            double averageSatisfaction = (double) tableSatisfaction / occupiedSeats;
            return Math.round(averageSatisfaction * 10.0) / 10.0;
        }
        return 0.0;
    }

    @Override
    public int compareTo(Table table) {
        return this.maxCapacity - table.maxCapacity;
    }
}
