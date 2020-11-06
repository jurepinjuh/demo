package com.jurepinjuh.demo.Mappers;

import com.jurepinjuh.demo.Models.Brand;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Brand brand=new Brand();
        brand.setId(rs.getInt("IDBRAND"));
        brand.setBrandName(rs.getString("BRANDNAME"));
        return brand;
    }
}
