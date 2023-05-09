package com.example.pidev.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String value;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="product_id")
    @ToString.Exclude
    @JsonBackReference(value = "product_detail")
    private Product product;



    @ManyToOne(cascade = CascadeType.PERSIST) // cascade the save operation to Attribute
    @JoinColumn(name = "attribute_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Attribute attribut;

}
