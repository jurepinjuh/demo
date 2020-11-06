package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Models.Role;
import org.springframework.stereotype.Repository;


public interface IRoleRepository {
    Role findByName(String name);
    Role findByID(int id);
}
