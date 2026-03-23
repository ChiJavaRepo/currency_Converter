package com.example.currency_Converter.Services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Map;

@Service
public class CurrencyConversionService {

    private static final Map<String, BigDecimal> RATES = Map.of(
            "NGN-USD", new BigDecimal("0.00080"),
            "USD-NGN", new BigDecimal("1250"),
            "NGN-EUR", new BigDecimal("0.00074"),
            "EUR-NGN", new BigDecimal("1350"),
            "USD-EUR", new BigDecimal("0.93"),
            "EUR-USD", new BigDecimal("1.075")
    );

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
