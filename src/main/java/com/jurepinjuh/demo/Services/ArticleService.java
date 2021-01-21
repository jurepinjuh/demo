package com.jurepinjuh.demo.Services;

import com.jurepinjuh.demo.Models.*;
import com.jurepinjuh.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    JpaArticleRepository articleRepository;

    @Autowired
    ICategoryRepository categoryRepository;

    @Autowired
    IBrandRepository brandRepository;

    @Autowired
    IGenderRepository genderRepository;

    @Autowired
    JpaItemRepository itemRepository;
    @Autowired
    MessageSource messageSource;

   public  Article getArticleByID(@PathVariable int id) throws Exception {
       return articleRepository.findById(id).orElseThrow(
               ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,messageSource.getMessage("msg.article.notExist",null, LocaleContextHolder.getLocale()))
       );
   }

   public Long deleteArticle(@PathVariable int id){
        Set<Item> items=  itemRepository.getAllByArticleId(id);
        for (Item item:
             items) {
            itemRepository.delete(item);
        }
        return articleRepository.deleteArticleById(id);
    }
   public ResponseEntity<?> editArticle(@RequestBody Article article){
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   public  Set<Article> getArticleByFilter( String brands,  String categories, String genders) {
        List<Integer> brandList=brandRepository.getAll().stream().map(Brand::getId).collect(Collectors.toList());
        List<Integer> categoryList=categoryRepository.getAll().stream().map(Category::getId).collect(Collectors.toList());
        List<Integer> genderList=genderRepository.getAll().stream().map(Gender::getId).collect(Collectors.toList());
        if (brands!=""){
            brandList=Arrays.stream(brands.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        }
        if (categories!=""){
            categoryList=Arrays.stream(categories.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        }
        if(genders!=""){
            genderList=Arrays.stream(genders.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        }
      return articleRepository.findAllByBrandIdIsInAndCategoryIdIsInAndGenderIdIsIn(brandList,categoryList,genderList);
    }
}
