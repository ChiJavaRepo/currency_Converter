package com.example.currency_Converter.Model;

public class ConversionRequest {

    private String FromCurrency;
    private String ToCurrency;
    private String Amount;

    public String getFromCurrency() {
        return FromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        FromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return ToCurrency;
    }

    public void setToCurrency(String toCurrency) {
        ToCurrency = toCurrency;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
