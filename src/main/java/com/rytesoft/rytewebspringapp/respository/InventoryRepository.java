package com.rytesoft.rytewebspringapp.respository;

import com.rytesoft.rytewebspringapp.model.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Transactional
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    // find all by naming like /inventory/name/{name}
    List<Inventory> findByNameContaining(String name);

    // find all whose price is less than or equal to /inventory/price/{price}
    List<Inventory> findByPriceLessThanEqual(int price);

    //Inventory findOne(long );
}