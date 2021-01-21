package com.jurepinjuh.demo.Models;

import javax.validation.constraints.NotEmpty;

public class Gender {

    private int id;
    @NotEmpty(message = "validation.gender.gendername.notEmpty")
    private String name;

    public Gender() {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
