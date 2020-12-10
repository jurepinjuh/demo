package com.jurepinjuh.demo.Models;

import javax.validation.constraints.NotEmpty;

public class Category {
    @NotEmpty()
    private int id;
    @NotEmpty()
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
