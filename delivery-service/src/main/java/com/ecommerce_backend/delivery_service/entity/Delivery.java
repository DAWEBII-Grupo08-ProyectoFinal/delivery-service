package com.ecommerce_backend.delivery_service.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name="deliveries")
public class Delivery {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private Long orderId;
 @Column(nullable=false) private String method;
 @Column(nullable=false) private String recipientName;
 @Column(nullable=false) private String phone;
 private String address; private String district; private String reference;
 private Long shippingOptionId; private Long pickupLocationId;
 @Column(nullable=false) private BigDecimal cost;
 @Column(nullable=false) private String status;
 @Column(nullable=false) private LocalDateTime createdAt;
 protected Delivery(){}
 public Delivery(Long orderId,String method,String recipientName,String phone,String address,String district,String reference,Long shippingOptionId,Long pickupLocationId,BigDecimal cost){
  this.orderId=orderId;this.method=method;this.recipientName=recipientName;this.phone=phone;this.address=address;this.district=district;this.reference=reference;this.shippingOptionId=shippingOptionId;this.pickupLocationId=pickupLocationId;this.cost=cost;this.status="PENDING";this.createdAt=LocalDateTime.now();
 }
 public Long getId(){return id;} public Long getOrderId(){return orderId;} public String getMethod(){return method;} public BigDecimal getCost(){return cost;} public String getStatus(){return status;} public LocalDateTime getCreatedAt(){return createdAt;}
}
