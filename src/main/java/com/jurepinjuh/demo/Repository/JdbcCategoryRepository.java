package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Mappers.CategoryMapper;
import com.jurepinjuh.demo.Models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCategoryRepository implements  ICategoryRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query("select * from [CATEGORY]",new CategoryMapper());
    }
}
