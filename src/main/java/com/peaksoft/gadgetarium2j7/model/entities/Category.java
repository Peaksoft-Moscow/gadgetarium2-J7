package com.peaksoft.gadgetarium2j7.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.gadgetarium2j7.model.enums.SubCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> products;
}
