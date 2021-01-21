package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Models.Item;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface JpaItemRepository extends CrudRepository<Item,Integer> {
    Long deleteAllByArticleId(int id);
    Set<Item> getAllByArticleId(int id);
}
