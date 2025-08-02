package com.example.gestionstock.repository;

import com.example.gestionstock.domain.Depot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepotRepository extends JpaRepository<Depot, Long> {
}
