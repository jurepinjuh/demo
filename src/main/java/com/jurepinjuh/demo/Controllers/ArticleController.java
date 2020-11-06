package com.jurepinjuh.demo.Controllers;


import com.jurepinjuh.demo.Models.Article;
import com.jurepinjuh.demo.Models.Purchase;
import com.jurepinjuh.demo.Repository.IArticleRepository;
import com.jurepinjuh.demo.Repository.JdbcArticleRepository;
import com.jurepinjuh.demo.Repository.JpaPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    IArticleRepository repository;
    @Autowired
    JpaPurchaseRepository jpaPurchaseRepository;

    @GetMapping("/article/getAll")
    List<Article> getAllArticles(){
        return repository.getAllArticles();
    }


    @GetMapping("/article/get/{id}")
    Article getArticleByID(int id) throws Exception {
        Article article=repository.getArticleByID(id);
        if (article==null) {
            throw new Exception("No article with given id");
        }
        return article;

    }

    @PostMapping("/article/add")
    Article addArticle(Article article){
        return repository.addArticle(article);
    }
    @PostMapping("/article/delete")
    Boolean deleteArticle(int id){
        return repository.deleteArticle(id);
    }
}
