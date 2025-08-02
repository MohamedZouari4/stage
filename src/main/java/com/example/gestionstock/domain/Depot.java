// src/main/java/com/example/gestionstock/domain/Depot.java
package com.example.gestionstock.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_depot")
    private Long idDepot;
    private String nomDepot;

    public Depot(Long idDepot, String nomDepot) {
        this.idDepot = idDepot;
        this.nomDepot = nomDepot;
    }

    public Depot() {
        super();
    }
}