package com.example.gestionstock.conf;

import com.example.gestionstock.domain.Article;
import com.example.gestionstock.domain.Depot;
import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.repository.ArticleRepository;
import com.example.gestionstock.repository.DepotRepository;
import com.example.gestionstock.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ArticleRepository articleRepository,
                                   DepotRepository depotRepository,
                                   StockRepository stockRepository) {
        return args -> {

            // Prevent duplication — run only if data is missing
            if (articleRepository.count() == 0 && depotRepository.count() == 0 && stockRepository.count() == 0) {

                // Create articles
                Article article1 = new Article();
                article1.setDesignationArticle("DOLIPRANE 1000 mg");
                article1 = articleRepository.save(article1);

                Article article2 = new Article();
                article2.setDesignationArticle("DOLVEN 400 mg");
                article2 = articleRepository.save(article2);

                // Create depots
                Depot depot1 = new Depot();
                depot1.setNomDepot("BLOC CENTRAL");
                depot1 = depotRepository.save(depot1);

                Depot depot2 = new Depot();
                depot2.setNomDepot("URGENCE");
                depot2 = depotRepository.save(depot2);

                // Create stocks
                stockRepository.save(new Stock(null, article1, depot1, 5, LocalDate.of(2024, 8, 5)));
                stockRepository.save(new Stock(null, article1, depot1, 3, LocalDate.of(2022, 2, 5)));
                stockRepository.save(new Stock(null, article1, depot1, 7, LocalDate.of(2021, 12, 17)));
                stockRepository.save(new Stock(null, article1, depot2, 15, LocalDate.of(2021, 8, 5)));
                stockRepository.save(new Stock(null, article1, depot2, 4, LocalDate.of(2022, 6, 22)));
                stockRepository.save(new Stock(null, article2, depot1, 6, LocalDate.of(2022, 8, 11)));

                System.out.println("Sample data initialized successfully.");
            } else {
                System.out.println("ℹSample data already exists. Skipping initialization.");
            }
        };
    }
}
