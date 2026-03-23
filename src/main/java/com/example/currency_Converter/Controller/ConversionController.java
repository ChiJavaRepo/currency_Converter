package com.example.currency_Converter.Controller;

import com.example.currency_Converter.Dto.ConversionResponse;
import com.example.currency_Converter.Model.ConversionRequest;
import com.example.currency_Converter.Services.CurrencyConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


    @RestController
    @RequestMapping("/api/v1/currency")
    public class ConversionController {

        private final CurrencyConversionService conversionService;

        public ConversionController(CurrencyConversionService conversionService) {
            this.conversionService = conversionService;
        }

        @PostMapping("/convert")
        public ResponseEntity<?> convert(@RequestBody ConversionRequest request) {
            try {
                String from = request.getFromCurrency();
                String to = request.getToCurrency();
                String amountString = request.getAmount();

                if (from == null || to == null || amountString == null) {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body("FromCurrency, ToCurrency and Amount are required.");
                }

                BigDecimal amount;
                try {
                    amount = new BigDecimal(amountString);
                } catch (NumberFormatException e) {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body("Amount must be a valid number.");
                }

                if (amount.compareTo(BigDecimal.ZERO) < 0) {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body("Amount cannot be negative.");
                }

                BigDecimal converted = conversionService.convert(from, to, amount);
                BigDecimal rate = conversionService.getRate(from, to);

                ConversionResponse response = new ConversionResponse(
                        from.toUpperCase(),
                        to.toUpperCase(),
                        amount,
                        converted,
                        rate
                );

                return ResponseEntity.ok(response);

            } catch (IllegalArgumentException ex) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ex.getMessage());
            } catch (Exception ex) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("An unexpected error occurred.");
            }
        }
    }

