package com.example.currency_Converter.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "curreency_Models")
@Getter
@Setter
@ToString
public class ConversionRequest {

    @Id
    @GeneratedValue
    public UUID id;

    @Column(name = "FromCurrency", nullable = false)
    public String FromCurrency;

    @Column (name = "ToCurrency", nullable = false)
    public String ToCurrency;

    @Column (name = "Ämount", nullable = false)
    public BigDecimal Amount;

    @Column (name = "Result", nullable = false)
    public String Result;

    public LocalDateTime CreatedAt;


//    private String FromCurrency;
//    private String ToCurrency;
//    private String Amount;
//
//    public String getFromCurrency() {
//        return FromCurrency;
//    }
//
//    public void setFromCurrency(String fromCurrency) {
//        FromCurrency = fromCurrency;
//    }
//
//    public String getToCurrency() {
//        return ToCurrency;
//    }
//
//    public void setToCurrency(String toCurrency) {
//        ToCurrency = toCurrency;
//    }
//
//    public String getAmount() {
//        return Amount;
//    }
//
//    public void setAmount(String amount) {
//        Amount = amount;
//    }
}
