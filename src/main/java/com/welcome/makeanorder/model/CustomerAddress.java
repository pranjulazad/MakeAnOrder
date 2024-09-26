package com.welcome.makeanorder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="customer_addresses")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long addressId;

    @NotNull
    private String locality;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String pinCode;

    @NotNull
    private String country;

    @NotNull(message = "Address Type must be provided")
    private String addressType;

    @Column(name = "customer_id")
    @JsonIgnore
    private long customerId;
}
