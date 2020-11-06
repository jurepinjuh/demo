package com.jurepinjuh.demo.Mappers;

import com.jurepinjuh.demo.Models.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category=new Category();
        category.setId(rs.getInt("IDCATEGORY"));
        category.setCategoryName(rs.getString("CATEGORYNAME"));
        return category;
    }
}
