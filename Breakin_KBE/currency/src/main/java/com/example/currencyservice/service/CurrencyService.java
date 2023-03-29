package com.example.currencyservice.service;

import com.example.currencyservice.model.Currency;
import org.springframework.stereotype.Service;

import static com.example.currencyservice.model.Currency.*;
@Service
public class CurrencyService {

    public Double switchAmount(double currentAmount, String currentCurrency, String newCurrency) {
        Currency tmpCurrent;
        Currency tmpNew;

        tmpCurrent = matchStringToCurrency(currentCurrency);
        tmpNew = matchStringToCurrency(newCurrency);

        return Currency.switchAmount(currentAmount, tmpCurrent, tmpNew);
    }

    private Currency matchStringToCurrency(String currency) {
        return switch (currency) {
            case "EUR" -> EUR;
            case "VND" -> VND;
            default -> USD;
        };
    }
}
