package com.ecommerce_backend.delivery_service.controller;
import com.ecommerce_backend.delivery_service.dto.*;
import com.ecommerce_backend.delivery_service.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/deliveries")
public class DeliveryController {
 private final DeliveryService service;
 public DeliveryController(DeliveryService service){this.service=service;}
 @PostMapping("/quote") public DeliveryQuoteResponse quote(@RequestBody DeliveryQuoteRequest request){return service.quote(request);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public void create(@RequestBody CreateDeliveryRequest request){service.create(request);}
}
