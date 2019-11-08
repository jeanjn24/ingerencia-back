package com.personal.ing.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.ing.test.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
