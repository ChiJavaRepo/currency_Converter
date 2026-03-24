package com.example.currency_Converter.Dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;


@Value
@AllArgsConstructor
public class ConversionResponse {

    public String FromCurrency;
    public String toCurrency;
    public BigDecimal Amount;
    public BigDecimal converted;
    public String rate;



}
