package com.rsr.payment_microservice.core.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RSRPayment")
public class RSRPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;

    @Column(unique = true)
    private UUID orderId;

    @Column(unique = true)
    private UUID userId;

    private double amountInEuro;

    private PaymentMethod paymentMethod;
}
