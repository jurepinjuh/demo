package com.jurepinjuh.demo.Mappers;

import com.jurepinjuh.demo.Models.User;
import com.jurepinjuh.demo.Repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Autowired
    IRoleRepository roleRepository;

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user=new User();
        user.setId(rs.getInt("IDUSER"));
        user.setContactNumber(rs.getString("CONTACTNUMBER"));
        user.setUsername(rs.getString("USERNAME"));
        user.setName(rs.getString("NAME"));
        user.setSurname(rs.getString("SURNAME"));
        user.setEmail(rs.getString("EMAIL"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setRole(roleRepository.findByID(rs.getInt("ROLEID")));
        return user;
    }
}
