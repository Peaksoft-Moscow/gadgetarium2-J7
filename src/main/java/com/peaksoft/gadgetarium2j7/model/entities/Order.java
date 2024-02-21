package com.peaksoft.gadgetarium2j7.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.gadgetarium2j7.model.enums.DeliveryOptions;
import com.peaksoft.gadgetarium2j7.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private int sum;
    private String userAddress;
    private LocalDate orderDate;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private DeliveryOptions deliveryOptions;
    private PaymentMethod paymentMethod;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "payCard_id")
    private PayCard payCard;
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "orderList")
    private List<Product> products;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "orders")
    private List<User> users;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "basket_id")
    private Basket basket;

}
