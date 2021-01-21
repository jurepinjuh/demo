package com.jurepinjuh.demo.Models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Brand {

    private int id;
    @NotEmpty(message = "validation.brand.brandname.notEmpty")
    private String brandName;

    public Brand() {
        this.id = id;
        this.brandName = brandName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
