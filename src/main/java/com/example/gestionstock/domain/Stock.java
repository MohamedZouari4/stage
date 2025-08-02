// src/main/java/com/example/gestionstock/domain/Stock.java
package com.example.gestionstock.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Entity
public class Stock {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock")
    private Long idStock;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    @ManyToOne
    private Article article;

    @ManyToOne
    private Depot depot;

    private int qte;
    private LocalDate datePeremption;

    public Long getIdStock() {
        return idStock;
    }

    public void setIdStock(Long idStock) {
        this.idStock = idStock;
    }

    public LocalDate getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(LocalDate datePeremption) {
        this.datePeremption = datePeremption;
    }

    public Stock(Long idStock, Article article, Depot depot, int qte, LocalDate datePeremption) {
        this.idStock = idStock;
        this.article = article;
        this.depot = depot;
        this.qte = qte;
        this.datePeremption = datePeremption;
    }

    public Stock() {
        super();
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}