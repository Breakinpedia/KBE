package com.example.currencyservice.model;
public enum Currency {
    USD(1.0), EUR(0.92), VND(23515);

    private double toUSD;

    Currency(double toUSD) {
        this.toUSD = toUSD;
    }

    public static double switchAmount(double currentAmount, Currency currentCurrency, Currency newCurrency) {
        if (currentCurrency == newCurrency) {
            return currentAmount;
        }

        if (currentCurrency == USD) {
            return currentAmount * newCurrency.toUSD;
        }

        return currentCurrencyNotUSD(currentAmount / currentCurrency.toUSD, newCurrency);
    }

    private static double currentCurrencyNotUSD(double amountInUSD, Currency newCurrency) {
        if (newCurrency == USD) {
            return amountInUSD;
        }

        return amountInUSD * newCurrency.toUSD;
    }

}
