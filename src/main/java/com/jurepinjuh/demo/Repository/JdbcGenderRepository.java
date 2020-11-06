package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Mappers.GenderMapper;
import com.jurepinjuh.demo.Models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcGenderRepository implements IGenderRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Gender> getAll() {
        return jdbcTemplate.query("select * from GENDERCATEGORY",new GenderMapper());
    }
}
