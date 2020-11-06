package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Models.User;

public interface IUserRepository {

    User getUserByID(int id);
    User getUser(String username);
    User AddUser(User user);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);

}
