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
@RequestMapping("api/article")
public class ArticleRessource {

    private final StockService stockService;
    private static final String ENTITY_NAME = "Stock";
    private final ArticleRepository articleRepository;
    private final DepotRepository depotRepository;

    public ArticleRessource(StockService stockService, ArticleRepository articleRepository, DepotRepository depotRepository) {
        this.stockService = stockService;
        this.articleRepository = articleRepository;
        this.depotRepository = depotRepository;
    }

    //http://localhost:8081/api/stock/test
    @GetMapping("/test")
    public String test() {
        return "Hello from /api/stock/test!";
    }

    //GET http://localhost:8081/api/stock/articles
    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getArticles() {
        RestPreconditions.checkFound(articleRepository.findAll(), "No articles found");
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getAllArticles());
    }

    //GET http://localhost:8081/api/stock/articles/search?keyword=DOL
    @GetMapping("/articles/search")
    public List<ArticleDTO> searchArticles(@RequestParam String keyword) {
        RestPreconditions.checkFound(keyword != null && !keyword.isEmpty(), "Keyword must not be empty");
        return stockService.searchArticles(keyword)
                .stream()
                .map(article -> new ArticleDTO(article.getIdArticle(), article.getDesignationArticle()))
                .toList();
    }

}
