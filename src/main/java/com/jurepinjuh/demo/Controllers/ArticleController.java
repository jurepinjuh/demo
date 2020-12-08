package com.jurepinjuh.demo.Controllers;


import com.jurepinjuh.demo.Models.Article;
import com.jurepinjuh.demo.Repository.IArticleRepository;
import com.jurepinjuh.demo.Repository.JpaArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/article/get/{id}")
    Article getArticleByID(@PathVariable int id) throws Exception {
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

    @PutMapping("admin/editProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> editArticle(@RequestBody  Article article){
        Optional<Article> toEdit=articleRepository.findById(article.getId());
        if (toEdit.isPresent()){
            Article articleEdit=toEdit.get();
            articleEdit.setImagePath(article.getImagePath());
            articleEdit.setName(article.getName());
            articleEdit.setBrandId(article.getBrandId());
            articleEdit.setCategoryId(article.getCategoryId());
            articleEdit.setDescription(article.getDescription());
            articleEdit.setGenderId(article.getGenderId());
            articleEdit.setPrice(article.getPrice());
            articleRepository.save(articleEdit);
           return new ResponseEntity<>(articleEdit, HttpStatus.OK);
        }
        else{
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/article/getFilter")
    Set<Article> getArticleByFilter(@RequestBody List<Integer> brands, List<Integer> categories, List<Integer> genders) {
        return articleRepository.findAllByBrandIdInAndCategoryIdInAndGenderIdIn(brands,categories,genders);
    }

}
