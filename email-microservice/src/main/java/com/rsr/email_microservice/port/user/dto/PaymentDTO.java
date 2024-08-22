package com.rsr.email_microservice.port.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentDTO {

    private UUID paymentId;

    private UUID orderId;

    private UUID userId;

    private double amountInEuro;

    @JsonCreator
    public PaymentDTO(@JsonProperty("paymentId") UUID paymentId, @JsonProperty("orderId") UUID orderId,
                      @JsonProperty("userId") UUID userId,
                      @JsonProperty("amountInEuro") double amountInEuro) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.userId = userId;
        this.amountInEuro = amountInEuro;
    }
}
