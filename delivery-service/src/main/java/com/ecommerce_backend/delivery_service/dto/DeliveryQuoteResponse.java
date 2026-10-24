package com.ecommerce_backend.delivery_service.dto;
import java.math.BigDecimal;
public record DeliveryQuoteResponse(String method,BigDecimal cost) {}
