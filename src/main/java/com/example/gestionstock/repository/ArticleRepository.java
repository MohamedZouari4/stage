package com.example.gestionstock.repository;

import com.example.gestionstock.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByDesignationArticleContainingIgnoreCase (String keyword);
}