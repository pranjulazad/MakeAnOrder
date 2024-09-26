package com.welcome.makeanorder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="sellers")
public class Seller {

    @Id
    @Column(name = "seller_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sellerId;

    @Size(min = 3, max = 20, message = "length of name smaller than 3 character is not acceptable")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number is not valid")
    private String mobile;

    private String password;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    @JsonIgnore
    private List<Product> products;

    @OneToMany(targetEntity = SellerAddress.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    private List<SellerAddress> sellerAddresses;
}
