package com.edugroup.dpv.mesEncheresMVC.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroup.dpv.mesEncheresMVC.metier.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {

}
