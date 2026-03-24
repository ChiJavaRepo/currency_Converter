package com.example.currency_Converter.Services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Map;

@Service
public class CurrencyConversionService {

    @Value("#{${app.exchange.rates}}")
    private Map<String,BigDecimal> RATES;
    public BigDecimal convert(String from, String to, BigDecimal amount) {
        String key = from.toUpperCase(Locale.ROOT) + "-" + to.toUpperCase(Locale.ROOT);
        BigDecimal rate = RATES.get(key);

        if (rate == null) {
            throw new IllegalArgumentException("Unsupported currency pair: " + key);
        }

        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getRate(String from, String to) {
        String key = from.toUpperCase(Locale.ROOT) + "-" + to.toUpperCase(Locale.ROOT);
        BigDecimal rate = RATES.get(key);

        if (rate == null) {
            throw new IllegalArgumentException("Unsupported currency pair: " + key);
        }
        return rate;
    }
}
