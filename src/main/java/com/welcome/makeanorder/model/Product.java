package com.welcome.makeanorder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String imageSrc;

    @NotNull
    private String productType;

    @NotNull
    private String subType;

    @NotNull
    @Min(0)
    @Max(1000)
    private double availability;

    @Column(name = "seller_id")
    @NotNull
    private long sellerId;
}
