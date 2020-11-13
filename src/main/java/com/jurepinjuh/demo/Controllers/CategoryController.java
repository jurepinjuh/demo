package com.jurepinjuh.demo.Controllers;

import com.jurepinjuh.demo.Models.Article;
import com.jurepinjuh.demo.Models.Brand;
import com.jurepinjuh.demo.Models.Category;
import com.jurepinjuh.demo.Models.Gender;
import com.jurepinjuh.demo.Repository.IBrandRepository;
import com.jurepinjuh.demo.Repository.ICategoryRepository;
import com.jurepinjuh.demo.Repository.IGenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class CategoryController {
    @Autowired
    IGenderRepository genderRepository;

    @Autowired
    ICategoryRepository categoryRepository;

    @Autowired
    IBrandRepository brandRepository;

    @GetMapping("/category/getAllGenders")
    List<Gender> getAllGenders(){
        return genderRepository.getAll();
    }

    @GetMapping("/category/getAllCategories")
    List<Category> getAllCategories(){
        return categoryRepository.getAll();
    }

    @GetMapping("/category/getAllBrands")
    List<Brand> getAllBrands(){
        return brandRepository.getAll();
    }
}
