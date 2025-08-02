package com.example.gestionstock.repository;

import com.example.gestionstock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByArticle_IdArticleAndDepot_IdDepotOrderByDatePeremptionAsc(Long idArticle, Long idDepo);
}

