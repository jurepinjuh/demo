package com.jurepinjuh.demo.Mappers;

import com.jurepinjuh.demo.Models.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenderMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Gender gender=new Gender();
        gender.setId(rs.getInt("IDGENDERCATEGORY"));
        gender.setName(rs.getString("CATEGORYNAME"));
        return gender;
    }
}
