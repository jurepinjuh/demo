package com.jurepinjuh.demo.Controllers;

import com.jurepinjuh.demo.Controllers.HttpModels.PurchaseRequest;
import com.jurepinjuh.demo.Models.Gender;
import com.jurepinjuh.demo.Models.Item;
import com.jurepinjuh.demo.Models.Purchase;
import com.jurepinjuh.demo.Repository.JpaItemRepository;
import com.jurepinjuh.demo.Repository.JpaPurchaseRepository;
import com.jurepinjuh.demo.Services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@CrossOrigin(origins="*")
public class PurchaseController {

    @Autowired
    JpaPurchaseRepository purchaseRepository;

    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/purchase/getAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    Set<Purchase> getAllPurchases(){
        return (Set<Purchase>) purchaseRepository.findAll();
    }

    @PostMapping("purchase/add")
    ResponseEntity<?> addPurchase(@Valid @RequestBody PurchaseRequest request){
     return purchaseService.addPurchase(request);
    }




}
