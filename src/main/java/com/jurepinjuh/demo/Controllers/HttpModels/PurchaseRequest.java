package com.jurepinjuh.demo.Controllers.HttpModels;

import com.jurepinjuh.demo.Models.Item;
import com.jurepinjuh.demo.Models.Purchase;

import java.util.List;

public class PurchaseRequest {
    public PurchaseRequest() {
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    private Purchase purchase;
    private List<Item> itemList;
}
