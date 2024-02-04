package com.peaksoft.gadgetarium2j7.model.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "specialProducts")

@Getter
@Setter
@NoArgsConstructor
public class SpecialProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Product> newProducts;
    @OneToMany
    private List<Product> discountProducts;
    @OneToMany
    private List<Product> recommendedProducts;
}
