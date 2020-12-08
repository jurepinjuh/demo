package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Models.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JpaPurchaseRepository extends CrudRepository<Purchase,Long>  {


}
