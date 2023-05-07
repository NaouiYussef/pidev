package com.example.pidev.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
  @JsonIgnore
    private List<LigneDeCommande> ligneDeCommandes = new ArrayList<>();

    @ManyToOne()

    User user;
    @Enumerated(EnumType.STRING)
    Eetat etat;
    Long Total;



}
