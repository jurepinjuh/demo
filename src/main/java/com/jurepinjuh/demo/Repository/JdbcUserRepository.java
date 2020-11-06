package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Mappers.ArticleMapper;
import com.jurepinjuh.demo.Mappers.UserMapper;
import com.jurepinjuh.demo.Models.User;
import com.jurepinjuh.demo.Services.IPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;

@Repository
public class JdbcUserRepository implements IUserRepository  {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    IPasswordService passwordService;

    @Autowired
    UserMapper userMapper;
    @Override
    public User getUserByID(int id) {
        User user=null;
        try {
            user=jdbcTemplate.queryForObject("select * from USER where IDUSER=?",new Object[]{id},userMapper);
        }
        catch(Exception exception){

        }
        return user;
    }

    @Override
    public User getUser(String username) {
        User user=null;
        try {
            user=jdbcTemplate.queryForObject("select * from [USER] where USERNAME=?",new Object[]{username},userMapper);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return user;
    }

    @Override
    public User AddUser(User user) {

        try {
            jdbcTemplate.update("insert into [USER](EMAIL,PASSWORD,USERNAME,ROLEID) VALUES(?,?,?,?)",
                    new Object[]{user.getEmail(),user.getPassword(),user.getUsername(),user.getRole().getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User();
    }

    @Override
    public Boolean existsByUserName(String username) {
        User user=null;
        try {
            user=jdbcTemplate.queryForObject("select * from [USER] where USERNAME=?",new Object[]{username},userMapper);
        }
        catch(Exception exception){

        }
        return user!=null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        User user=null;
        try {
            user=jdbcTemplate.queryForObject("select * from [USER] where EMAIL=?",new Object[]{email},userMapper);
        }
        catch(Exception exception){

        }
        return user!=null;
    }
}
