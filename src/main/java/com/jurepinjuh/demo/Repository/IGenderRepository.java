package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Models.Gender;

import java.util.List;

public interface IGenderRepository {
    List<Gender> getAll();
}
