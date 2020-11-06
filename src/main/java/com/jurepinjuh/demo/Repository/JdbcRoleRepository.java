package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Mappers.RoleMapper;
import com.jurepinjuh.demo.Models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRoleRepository implements IRoleRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Role findByName(String name) {
        Role role=null;
        try {
        role=jdbcTemplate.queryForObject("select * from ROLES WHERE ROLENAME=?",new Object[]{name}, new RoleMapper());
        }
        catch (Exception ex){
        }
        return role;
    }

    @Override
    public Role findByID(int id) {
        Role role=null;
        try {
            role=jdbcTemplate.queryForObject("select * from ROLES WHERE ID=?",new Object[]{id}, new RoleMapper());
        }
        catch (Exception ex){
        }
        return role;
    }
}
