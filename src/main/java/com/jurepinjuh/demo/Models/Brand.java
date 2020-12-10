package com.jurepinjuh.demo.Models;

import javax.validation.constraints.NotEmpty;

public class Brand {
    @NotEmpty()
    private int id;
    @NotEmpty()
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
