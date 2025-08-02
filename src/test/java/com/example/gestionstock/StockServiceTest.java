package com.example.gestionstock;

import com.example.gestionstock.domain.Article;
import com.example.gestionstock.domain.Depot;
import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.repository.ArticleRepository;
import com.example.gestionstock.repository.DepotRepository;
import com.example.gestionstock.repository.StockRepository;
import com.example.gestionstock.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockServiceTest {

    private StockRepository stockRepository;
    private ArticleRepository articleRepository;
    private DepotRepository depotRepository;
    private StockService stockService;

    @BeforeEach
    void setUp() {
        stockRepository = mock(StockRepository.class);
        articleRepository = mock(ArticleRepository.class);
        depotRepository = mock(DepotRepository.class);
        stockService = new StockService(stockRepository, articleRepository, depotRepository);
    }

    @Test
    void testGetCurrentStock() {
        Long articleId = 1L;
        Long depotId = 1L;

        Stock stock1 = new Stock(null, new Article(), new Depot(), 5, LocalDate.now().plusDays(10));
        Stock stock2 = new Stock(null, new Article(), new Depot(), 3, LocalDate.now().plusDays(20));

        when(stockRepository.findByArticle_IdArticleAndDepot_IdDepotOrderByDatePeremptionAsc(articleId, depotId))
                .thenReturn(List.of(stock1, stock2));

        int result = stockService.getCurrentStock(articleId, depotId);
        assertEquals(8, result);
    }
}
