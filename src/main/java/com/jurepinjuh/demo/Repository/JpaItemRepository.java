package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Models.Item;
import org.springframework.data.repository.CrudRepository;

public interface JpaItemRepository extends CrudRepository<Item,Integer> {
}
