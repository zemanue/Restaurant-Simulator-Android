package com.example.restaurantsimulator;

import java.util.Random;

public class Customer {
    Random random = new Random();
    private int satisfactionLevel;
    private int patienceLevel;
    private boolean prefersWindow;
    private double spending;
    private double tip;

    public Customer() {
        this.patienceLevel = (random.nextInt(10) + 1);
        this.prefersWindow = random.nextBoolean();
    }

    public Customer(int patienceLevel, boolean prefersWindow) {
        this.patienceLevel = patienceLevel;
        this.prefersWindow = prefersWindow;
    }

    public int getSatisfactionLevel() {
        return satisfactionLevel;
    }

    public void setSatisfactionLevel(int satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel;
    }

    public int getPatienceLevel() {
        return patienceLevel;
    }

    public void setPatienceLevel(int patienceLevel) {
        this.patienceLevel = patienceLevel;
    }

    public boolean getPrefersWindow() {
        return prefersWindow;
    }

    public void setPrefersWindow(boolean prefersWindow) {
        this.prefersWindow = prefersWindow;
    }

    public double calculateSpending() {
        // Random number between 10 and 20, rounded to 1 decimal place
        spending = Math.round((random.nextDouble() * 10 + 10) * 10.0) / 10.0;
        return spending;
    }

    public double calculateTip() {
        double minTipPercentage = 0.0;
        double maxTipPercentage = 0.0;

        // Tip percentage range based on satisfaction level
        switch (satisfactionLevel) {
            case 5:
                minTipPercentage = 0.10;
                maxTipPercentage = 0.20;
                break;
            case 4:
                minTipPercentage = 0.08;
                maxTipPercentage = 0.16;
                break;
            case 3:
                minTipPercentage = 0.06;
                maxTipPercentage = 0.12;
                break;
            case 2:
                minTipPercentage = 0.04;
                maxTipPercentage = 0.08;
                break;
            case 1:
                minTipPercentage = 0.00;
                maxTipPercentage = 0.00;
                break;
        }

        // Random tip percentage within the range based on satisfaction level
        double tipPercentage = minTipPercentage + (random.nextDouble() * (maxTipPercentage - minTipPercentage));
        tip = Math.round(spending * tipPercentage * 100.0) / 100.0;
        return tip;
    }
}
