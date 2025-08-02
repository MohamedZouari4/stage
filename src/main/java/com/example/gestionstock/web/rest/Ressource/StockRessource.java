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
@RequestMapping("api/stock")
public class StockRessource {

    private final StockService stockService;
    private static final String ENTITY_NAME = "Stock";
    private final ArticleRepository articleRepository;
    private final DepotRepository depotRepository;

    public StockRessource(StockService stockService, ArticleRepository articleRepository, DepotRepository depotRepository) {
        this.stockService = stockService;
        this.articleRepository = articleRepository;
        this.depotRepository = depotRepository;
    }

    //http://localhost:8081/api/stock/test
    @GetMapping("/test")
    public String test() {
        return "Hello from /api/stock/test!";
    }

/*    //GET http://localhost:8081/api/stock/articles
    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getArticles() {
        RestPreconditions.checkFound(articleRepository.findAll(), "No articles found");
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getAllArticles());
    }*/

/*    //GET http://localhost:8081/api/stock/depots
    @GetMapping("/depots")
    public List<Depot> getDepots() {
        RestPreconditions.checkFound(depotRepository.findAll(), "No depots found");
        return stockService.getAllDepots();
    }*/
    //GET http://localhost:8081/api/stock/articles/search?keyword=DOL
/*    @GetMapping("/articles/search")
    public List<ArticleDTO> searchArticles(@RequestParam String keyword) {
        RestPreconditions.checkFound(keyword != null && !keyword.isEmpty(), "Keyword must not be empty");
        return stockService.searchArticles(keyword)
                .stream()
                .map(article -> new ArticleDTO(article.getIdArticle(), article.getDesignationArticle()))
                .toList();
    }*/
    //GET http://localhost:8081/api/stock?articleId=1&depotId=1
    @GetMapping("")
    public int getStock(@RequestParam Long articleId, @RequestParam Long depotId) {
        RestPreconditions.checkFound(articleRepository.existsById(articleId), "Article not found");
        return stockService.getCurrentStock(articleId, depotId);
    }
    //GET http://localhost:8081/api/stock/1
    @GetMapping("/{id}")//we should check it again
    public Stock getStockById(@PathVariable Integer id) {
        Stock stock = stockService.getStockById(id);
        RestPreconditions.checkFound(stock,ENTITY_NAME+" not found");
        return stockService.getStockById(id);
    }
    //http://localhost:8081/api/stock
    /*
    {
  "articleId": 1,
  "depotId": 2,
  "qte": 30,
  "datePeremption": "2024-12-05"
}

     */
    @PostMapping("")//we should check it again
    public ResponseEntity<String> addStock(@RequestBody StockDTO stockDTO) {
        stockService.addStock(stockDTO);
        return ResponseEntity.ok("Stock added successfully");
    }
    //http://localhost:8081/api/stock
    /*
    {
  "articleId": 1,
  "depotId": 1,
  "qte": 13
}
     */
    @PutMapping("")//check this
    public ResponseEntity<String> removeStock(@RequestBody StockDTO stockDTO) {
        RestPreconditions.checkFound(articleRepository.existsById(stockDTO.getArticleId()), "Article not found");
        RestPreconditions.checkFound(depotRepository.existsById(stockDTO.getDepotId()), "Depot not found");
        stockService.removeStock(stockDTO.getArticleId(), stockDTO.getDepotId(), stockDTO.getQte());
        return ResponseEntity.ok("Stock removed successfully");
    }
    // DELETE http://localhost:8081/api/stock/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Integer id) {
        RestPreconditions.checkFound(stockService.getStockById(id), ENTITY_NAME + " not found");
        stockService.deleteStock(id);
        return ResponseEntity.ok(ENTITY_NAME + " deleted successfully");
    }
}
