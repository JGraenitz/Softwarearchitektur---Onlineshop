package com.rsr.email_microservice.port.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class ItemDTO {

    private UUID productId;

    private String productName;

    private int amount;

    private double priceInEuro;

    @JsonCreator
    public ItemDTO(@JsonProperty("productId") UUID productId, @JsonProperty("productName") String productName,
                   @JsonProperty("amount") int amount, @JsonProperty("priceInEuro") double priceInEuro) {
        this.productId = productId;
        this.productName = productName;
        this.amount = amount;
        this.priceInEuro = priceInEuro;
    }
}
