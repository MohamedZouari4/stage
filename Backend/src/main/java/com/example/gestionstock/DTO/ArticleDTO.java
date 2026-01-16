package com.example.gestionstock.DTO;

public class ArticleDTO {
    private Long idArticle;
    private String designationArticle;

    public ArticleDTO(Long idArticle, String designationArticle) {
        this.idArticle = idArticle;
        this.designationArticle = designationArticle;
    }

    public Long getIdArticle() {
        return idArticle;
    }

    public String getDesignationArticle() {
        return designationArticle;
    }
}
