package com.example.gestionstock.web.rest.Ressource;

import com.example.gestionstock.DTO.ArticleDTO;
import com.example.gestionstock.DTO.StockDTO;
import com.example.gestionstock.domain.Article;
import com.example.gestionstock.domain.Depot;
import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.repository.ArticleRepository;
import com.example.gestionstock.repository.DepotRepository;
import com.example.gestionstock.service.StockService;
import com.example.gestionstock.util.RestPreconditions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/depot")
public class DepotRessource {

    private final StockService stockService;
    private static final String ENTITY_NAME = "Stock";
    private final ArticleRepository articleRepository;
    private final DepotRepository depotRepository;

    public DepotRessource(StockService stockService, ArticleRepository articleRepository, DepotRepository depotRepository) {
        this.stockService = stockService;
        this.articleRepository = articleRepository;
        this.depotRepository = depotRepository;
    }

    //http://localhost:8081/api/stock/test
    @GetMapping("/test")
    public String test() {
        return "Hello from /api/stock/test!";
    }


    //GET http://localhost:8081/api/stock/depots
    @GetMapping("/depots")
    public List<Depot> getDepots() {
        RestPreconditions.checkFound(depotRepository.findAll(), "No depots found");
        return stockService.getAllDepots();
    }

}

