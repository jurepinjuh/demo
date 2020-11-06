package com.jurepinjuh.demo.Mappers;

import com.jurepinjuh.demo.Models.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role=new Role();
        role.setId(rs.getInt("ID"));
        role.setName(rs.getString("ROLENAME"));
        return role;
    }
}
