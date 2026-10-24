package com.ecommerce_backend.delivery_service.service;
import com.ecommerce_backend.delivery_service.dto.*;
import com.ecommerce_backend.delivery_service.entity.Delivery;
import com.ecommerce_backend.delivery_service.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Set;
@Service
public class DeliveryService {
 private static final Set<String> METHODS=Set.of("STANDARD","EXPRESS","PICKUP");
 private final DeliveryRepository repository;
 @Value("${delivery.rates.standard:10.00}") private BigDecimal standardRate;
 @Value("${delivery.rates.express:20.00}") private BigDecimal expressRate;
 @Value("${delivery.rates.pickup:0.00}") private BigDecimal pickupRate;
 public DeliveryService(DeliveryRepository repository){this.repository=repository;}
 public DeliveryQuoteResponse quote(DeliveryQuoteRequest request){String method=normalize(request.method());return new DeliveryQuoteResponse(method,price(method));}
 @Transactional
 public void create(CreateDeliveryRequest r){
  if(r.orderId()==null)throw new IllegalArgumentException("orderId es obligatorio");
  if(r.recipientName()==null||r.recipientName().isBlank())throw new IllegalArgumentException("recipientName es obligatorio");
  if(r.phone()==null||r.phone().isBlank())throw new IllegalArgumentException("phone es obligatorio");
  String method=normalize(r.method());
  if(repository.findByOrderId(r.orderId()).isPresent())throw new IllegalStateException("Ya existe una entrega para el pedido "+r.orderId());
  if(method.equals("PICKUP")&&r.pickupLocationId()==null)throw new IllegalArgumentException("pickupLocationId es obligatorio para recojo");
  if(!method.equals("PICKUP")&&(r.address()==null||r.address().isBlank()))throw new IllegalArgumentException("address es obligatorio para entrega a domicilio");
  repository.save(new Delivery(r.orderId(),method,r.recipientName(),r.phone(),r.address(),r.district(),r.reference(),r.shippingOptionId(),r.pickupLocationId(),price(method)));
 }
 private String normalize(String value){
  if(value==null||value.isBlank())throw new IllegalArgumentException("method es obligatorio");
  String m=value.trim().toUpperCase(Locale.ROOT);
  if(!METHODS.contains(m))throw new IllegalArgumentException("Método inválido: usa STANDARD, EXPRESS o PICKUP");
  return m;
 }
 private BigDecimal price(String method){return switch(method){case "STANDARD"->standardRate;case "EXPRESS"->expressRate;case "PICKUP"->pickupRate;default->throw new IllegalArgumentException("Método no válido");};}
}
