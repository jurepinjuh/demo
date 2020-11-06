package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Mappers.ArticleMapper;
import com.jurepinjuh.demo.Mappers.BrandMapper;
import com.jurepinjuh.demo.Models.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcBrandRepository implements IBrandRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Brand> getAll() {
        return jdbcTemplate.query("select * from [BRAND]",new BrandMapper());
    }
}
