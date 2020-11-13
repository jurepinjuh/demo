package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Models.Article;
import java.util.List;


public interface IArticleRepository {
    List<Article> getAllArticles();
    List<Article>getHomePageArticles();
    Article getArticleByID(int id);
    Article addArticle(Article article);
    boolean deleteArticle(int id);
}
