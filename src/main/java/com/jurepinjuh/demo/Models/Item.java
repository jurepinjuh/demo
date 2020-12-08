package com.jurepinjuh.demo.Models;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;

@Table(name="ITEM")
@Entity
public class Item {
    @Column(name="IDITEM")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="QUANTITY")
    private int quantity;
    @Column(name="ARTICLEID")
    private int articleId;
    @Column(name="TOTALITEM")
    private double total;
    @Column(name="PURCHASEID")
    private int purchaseId;

    public Item(){

    }
    public Item(int id, int quantity, int articleId, double total, int purchaseId) {
        this.id = id;
        this.quantity = quantity;
        this.articleId = articleId;
        this.total = total;
        this.purchaseId = purchaseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }
}
