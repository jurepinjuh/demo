package com.jurepinjuh.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name="ARTICLE")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDARTICLE")
    private int id;
    @NotEmpty(message = "validation.article.name.notEmpty")
    @Column(name = "NAME")
    private String name;
    @NotEmpty(message = "validation.article.description.notEmpty")
    @Column(name = "DESCRIPTION")
    private String description;
    @NotNull(message = "validation.article.categoryid.notNull")
    @Column(name="CATEGORYID")
    private int categoryId;
    @NotNull(message = "validation.article.gendercategory.notNull")
    @Column(name="GENDERCATEGORY")
    private int genderId;
    @NotNull(message = "validation.article.brandid.notNull")
    @Column(name="BRANDID")
    private int brandId;
    @NotEmpty(message = "validation.article.imagepath.notEmpty")
    @Column(name="IMAGEPATH")
    private String imagePath;
    @NotNull(message = "validation.article.price.notNull")
    @Column(name="PRICE")
    private double price;

    public Article(int id, String name, String description, int categoryId, int genderId, int brandId, String imagePath, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.genderId = genderId;
        this.brandId = brandId;
        this.imagePath = imagePath;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Article() {
    }
}
