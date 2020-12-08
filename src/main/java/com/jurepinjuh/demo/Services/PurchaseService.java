package com.jurepinjuh.demo.Services;

import com.jurepinjuh.demo.Controllers.HttpModels.PurchaseRequest;
import com.jurepinjuh.demo.Models.Item;
import com.jurepinjuh.demo.Models.Purchase;
import com.jurepinjuh.demo.Repository.JpaItemRepository;
import com.jurepinjuh.demo.Repository.JpaPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    JpaPurchaseRepository purchaseRepository;

    @Autowired
    JpaItemRepository itemRepository;

   public  ResponseEntity<?> addPurchase(@RequestBody PurchaseRequest request){
        Purchase purchase=null;
        List<Item> insertedItems=new ArrayList<>();
        try {
            purchase=purchaseRepository.save(request.getPurchase());
        }
        catch (Exception e){
            return new ResponseEntity(purchase, HttpStatus.BAD_REQUEST);
        }
        if (purchase!=null){
            for (Item item:request.getItemList()) {
                item.setPurchaseId(purchase.getId());
                try{
                    Item insertedItem=itemRepository.save(item);
                    insertedItems.add(insertedItem);
                }
                catch (Exception exception){
                    itemRepository.deleteAll(insertedItems);
                    purchaseRepository.delete(purchase);
                    return new ResponseEntity(purchase, HttpStatus.BAD_REQUEST);
                }
            }
        }
        return new ResponseEntity(purchase,HttpStatus.OK);
    }
}
