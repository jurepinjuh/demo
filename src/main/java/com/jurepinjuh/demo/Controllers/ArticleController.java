package com.jurepinjuh.demo.Controllers;


import com.jurepinjuh.demo.Models.Article;
import com.jurepinjuh.demo.Repository.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class ArticleController {

    @Autowired
    IArticleRepository repository;

    @GetMapping("/article/getAll")
    List<Article> getAllArticles(){
        return repository.getAllArticles();
    }

    @GetMapping("/article/getHomePage")
    List<Article> getRand8(){
        return repository.getHomePageArticles();
    }

    @GetMapping("/article/get/{id}")
    Article getArticleByID(int id) throws Exception {
        Article article=repository.getArticleByID(id);
        if (article==null) {
            throw new Exception("No article with given id");
        }
        return article;
    }

    @PostMapping("/admin/addProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    Article addArticle(@RequestBody  Article article){
        return repository.addArticle(article);
    }

    @DeleteMapping("/admin/deleteProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    Boolean deleteArticle(@RequestBody int id){
        return repository.deleteArticle(id);
    }
}
