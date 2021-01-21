package com.jurepinjuh.demo.Models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Category {

    private int id;
    @NotEmpty(message = "validation.category.categoryname.notEmpty")
    private String categoryName;

    public Category() {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
