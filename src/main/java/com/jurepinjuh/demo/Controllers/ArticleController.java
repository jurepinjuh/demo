package com.jurepinjuh.demo.Controllers;


import com.jurepinjuh.demo.Models.Article;
import com.jurepinjuh.demo.Repository.IArticleRepository;
import com.jurepinjuh.demo.Repository.JpaArticleRepository;
import com.jurepinjuh.demo.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins="*")
public class ArticleController {

    @Autowired
    IArticleRepository repository;

    @Autowired
    JpaArticleRepository articleRepository;

    @GetMapping("/article/getAll")
    List<Article> getAllArticles(){
        return repository.getAllArticles();
    }

    @GetMapping("/article/getHomePage")
    List<Article> getRand8(){
        return repository.getHomePageArticles();
    }

    @Autowired
    ArticleService articleService;

    @GetMapping("/article/get/{id}")
    Article getArticleByID(@PathVariable int id) throws Exception {
        return articleService.getArticleByID(id);
    }

    @PostMapping("/admin/addProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    Article addArticle(@Valid @RequestBody  Article article){
        return articleRepository.save(article);
    }

    @DeleteMapping("/admin/deleteProduct/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Long deleteArticle(@PathVariable int id){
        return articleService.deleteArticle(id);
    }

    @PutMapping("admin/editProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> editArticle(@Valid @RequestBody  Article article){
    return articleService.editArticle(article);
    }

    @GetMapping("/article/getFilter")
    Set<Article> getArticleByFilter(@RequestParam String brands,@RequestParam String categories,@RequestParam String genders) {
      return articleService.getArticleByFilter(brands,categories,genders);
    }

}
