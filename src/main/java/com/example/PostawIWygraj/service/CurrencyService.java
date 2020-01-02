package com.example.PostawIWygraj.service;

import java.util.List;

import com.example.PostawIWygraj.model.Currency;

public interface CurrencyService {
    public List<Currency> getAllCurency();

    public void save(Currency currency);

    public Currency getActualCurrency();

    public void getActualCurrencyFromNBP();
}
