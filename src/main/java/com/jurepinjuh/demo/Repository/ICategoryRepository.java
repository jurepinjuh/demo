package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Models.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> getAll();
}
