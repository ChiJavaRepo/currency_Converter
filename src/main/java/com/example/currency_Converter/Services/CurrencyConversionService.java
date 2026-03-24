package com.example.currency_Converter.Services;


import com.example.currency_Converter.Model.ConversionRequest;
import com.example.currency_Converter.repository.currencyConversionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

@Service
public class CurrencyConversionService {

    @Value("#{${app.exchange.rates}}")
    private Map<String,BigDecimal> RATES;
    private currencyConversionRepository conversionRepository;
    public CurrencyConversionService(currencyConversionRepository conversionRepository,
                                     Map<String, BigDecimal> rates) {
        this.conversionRepository = conversionRepository;
        this.RATES = rates;
    }


    //convert method
    public BigDecimal convert(String from, String to, BigDecimal amount) {
        String key = from.toUpperCase(Locale.ROOT) + "-" + to.toUpperCase(Locale.ROOT);
        BigDecimal rate = RATES.get(key);

        if (rate == null) {
            throw new IllegalArgumentException("Unsupported currency pair: " + key);
        }

        BigDecimal result = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);

        ConversionRequest conversionRequest = new ConversionRequest();
            conversionRequest.setFromCurrency(from.toUpperCase(Locale.ROOT));
            conversionRequest.setToCurrency(from.toUpperCase(Locale.ROOT));
            conversionRequest.setAmount(amount);
            conversionRequest.setResult(String.valueOf(result));
            conversionRequest.setCreatedAt(LocalDateTime.now());
            conversionRepository.save(conversionRequest);

        return result;
    }

    // getrate method
    public BigDecimal getRate(String from, String to) {
        String key = from.toUpperCase(Locale.ROOT) + "-" + to.toUpperCase(Locale.ROOT);
        BigDecimal rate = RATES.get(key);

        if (rate == null) {
            throw new IllegalArgumentException("Unsupported currency pair: " + key);
        }
        return rate;
    }
}
