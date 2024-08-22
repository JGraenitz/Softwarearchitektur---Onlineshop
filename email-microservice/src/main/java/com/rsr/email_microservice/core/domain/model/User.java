package com.rsr.email_microservice.core.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserAccount")
public class User {

    @Id
    private UUID userId;

    private String firstName;

    private String lastName;

    private String emailAddress;

}
