package com.example.currency_Converter.repository;

import com.example.currency_Converter.Model.ConversionRequest;

import java.util.UUID;

public interface currencyConversionRepository extends jpaRepository<ConversionRequest, UUID>{
}
