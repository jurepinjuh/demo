package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Models.Article;
import com.jurepinjuh.demo.Models.Brand;
import com.jurepinjuh.demo.Models.Category;
import com.jurepinjuh.demo.Models.Gender;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface JpaArticleRepository extends CrudRepository<Article,Integer> {
    Set<Article> findAllByBrandIdInAndCategoryIdInAndGenderIdIn(List<Integer>brands, List<Integer>categories, List<Integer>genders);
}
