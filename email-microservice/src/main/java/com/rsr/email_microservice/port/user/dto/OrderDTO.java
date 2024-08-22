package com.rsr.email_microservice.port.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {

    private UUID orderId;

    private UUID userId;

    private String firstName;

    private String lastName;

    private LocalDateTime orderCreationTime;

    private String emailAddress;

    private List<ItemDTO> items;

    @JsonCreator
    public OrderDTO(@JsonProperty("orderId") UUID orderId, @JsonProperty("userId") UUID userId,
                    @JsonProperty("firstName") String firstName,
                    @JsonProperty("lastName") String lastName,
                    @JsonProperty("orderCreationTime") LocalDateTime orderCreationTime,
                    @JsonProperty("emailAddress") String emailAddress,
                    @JsonProperty("items") List<ItemDTO> items) {
        this.orderId = orderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderCreationTime = orderCreationTime;
        this.emailAddress = emailAddress;
        this.items = items;
        this.userId = userId;
    }

}
