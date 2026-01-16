package com.example.gestionstock.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDTO {


    private Long idStock;
    @NotNull(message = "Article ID is required")
    private Long articleId;

    @NotNull(message = "Depot ID is required")
    private Long depotId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer qte;

    @FutureOrPresent(message = "Expiration date must be in the present or future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePeremption;

    // Constructors
    public StockDTO() {}

    public StockDTO(Long articleId, Long depotId, Integer qte, LocalDate datePeremption) {
        this.articleId = articleId;
        this.depotId = depotId;
        this.qte = qte;
        this.datePeremption = datePeremption;
    }

    // Getters and Setters
    public Long getIdStock() {
        return idStock;
    }

    public void setIdStock(Long idStock) {
        this.idStock = idStock;
    }
    public Long getArticleId() { return articleId; }
    public void setArticleId(Long articleId) { this.articleId = articleId; }

    public Long getDepotId() { return depotId; }
    public void setDepotId(Long depotId) { this.depotId = depotId; }

    public Integer getQte() { return qte; }
    public void setQte(Integer qte) { this.qte = qte; }

    public LocalDate getDatePeremption() { return datePeremption; }
    public void setDatePeremption(LocalDate datePeremption) { this.datePeremption = datePeremption; }


}