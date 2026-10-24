package com.ecommerce_backend.delivery_service.dto;
public record DeliveryQuoteRequest(String method,Long shippingOptionId,Long pickupLocationId) {}
