package com.example.currency_Converter.repository;

import com.example.currency_Converter.Model.ConversionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface currencyConversionRepository extends JpaRepository<ConversionRequest, UUID> {
}
