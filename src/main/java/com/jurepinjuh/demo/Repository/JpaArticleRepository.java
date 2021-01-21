package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Models.Article;
import com.jurepinjuh.demo.Models.Brand;
import com.jurepinjuh.demo.Models.Category;
import com.jurepinjuh.demo.Models.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
public interface JpaArticleRepository extends CrudRepository<Article,Integer> {
    Set<Article> findAllByBrandIdIsInAndCategoryIdIsInAndGenderIdIsIn(List<Integer>brands, List<Integer>categories, List<Integer>genders);
    Set<Article> findAllByBrandIdIn(List<Integer> brands);
    Optional<Article> findArticleByName(String name);
    Long deleteArticleById(int id);
}
