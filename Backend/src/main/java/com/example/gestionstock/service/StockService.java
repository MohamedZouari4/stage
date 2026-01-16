package com.example.gestionstock.service;

import com.example.gestionstock.DTO.StockDTO;
import com.example.gestionstock.domain.Article;
import com.example.gestionstock.domain.Depot;
import com.example.gestionstock.domain.Stock;
import com.example.gestionstock.repository.ArticleRepository;
import com.example.gestionstock.repository.DepotRepository;
import com.example.gestionstock.repository.StockRepository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository; //bech yetssan3o mara barka meho bech immesshom 7atta 7ad
    private final ArticleRepository articleRepository;
    private final DepotRepository depotRepository;

    @Autowired
    public StockService(StockRepository stockRepository, ArticleRepository articleRepository, DepotRepository depotRepository) {
        this.stockRepository = stockRepository;
        this.articleRepository = articleRepository;
        this.depotRepository = depotRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
    public List<Depot> getAllDepots(){
        return depotRepository.findAll();
    }
    public Stock getStockById(Integer id) {
        return stockRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Stock not found"));
    }
    public List<Article> searchArticles(String keyword) {
        return articleRepository.findByDesignationArticleContainingIgnoreCase(keyword);
    }

    public int getCurrentStock(Long articleId, Long depotId) {
        return stockRepository
                .findByArticle_IdArticleAndDepot_IdDepotOrderByDatePeremptionAsc(articleId, depotId)
                .stream()
                .mapToInt(Stock::getQte)
                .sum();
    }
    public List<Stock> getStockByArticleAndDepot(Long articleId, Long depotId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        Depot depot = depotRepository.findById(depotId)
                .orElseThrow(() -> new RuntimeException("Depot not found"));

        return stockRepository.findByArticle_IdArticleAndDepot_IdDepotOrderByDatePeremptionAsc(articleId, depotId);
    }

    public void addStock(StockDTO stockDTO) {
        // Validate DTO (additional validation beyond annotations)
        if (stockDTO.getDatePeremption().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date cannot be in the past");
        }

        // Fetch related entities
        Article article = articleRepository.findById(stockDTO.getArticleId())
                .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + stockDTO.getArticleId()));

        Depot depot = depotRepository.findById(stockDTO.getDepotId())
                .orElseThrow(() -> new EntityNotFoundException("Depot not found with id: " + stockDTO.getDepotId()));

        // Create and save new stock entry
        Stock stock = new Stock();
        stock.setArticle(article);
        stock.setDepot(depot);
        stock.setQte(stockDTO.getQte());
        stock.setDatePeremption(stockDTO.getDatePeremption());

        stockRepository.save(stock);
    }
    @Transactional(readOnly = true)
    public void removeStock(Long articleId, Long depotId, int qteToRemove) {
        List<Stock> stocks = stockRepository.findByArticle_IdArticleAndDepot_IdDepotOrderByDatePeremptionAsc(articleId, depotId);

        int remaining = qteToRemove;

        for (Stock stock : stocks) {  // replace by fonction lambda
            if (stock.getQte() >= remaining) {
                stock.setQte(stock.getQte() - remaining);
                stockRepository.save(stock);
                return;
            } else {
                remaining -= stock.getQte();
                stock.setQte(0);
                stockRepository.save(stock);
            }
        }
        if (remaining > 0) {
            throw new RuntimeException("Quantité insuffisante en stock !");
        }
    }

    public void deleteStock(Integer id) {
        Stock stock = stockRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Stock not found with id: " + id));
        stockRepository.delete(stock);
    }
}