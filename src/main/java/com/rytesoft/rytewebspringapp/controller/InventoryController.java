package com.rytesoft.rytewebspringapp.controller;

import com.rytesoft.rytewebspringapp.model.Inventory;
import com.rytesoft.rytewebspringapp.respository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InventoryController {

    private final InventoryRepository itemsRepo;

    public InventoryController(InventoryRepository itemsRepo) {
        this.itemsRepo = itemsRepo;
    }

    /**
     * check
     */
    @RequestMapping("/check")
    @ResponseBody
    String check() {
        return "Hello World!";
    }

    /**
     * @return all items in inventory
     */
    //@HystrixCommand(fallbackMethod="failGood")
    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    @ResponseBody Iterable<Inventory> getInventory() {
        return itemsRepo.findAll();
    }

    /**
     * @return item by id
     */
    //@RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
    //@ResponseBody
    //Inventory getById(@PathVariable long id) {
       // return itemsRepo.findById(id);
    //}

    /**
     * @return item(s) containing name
     */
    @RequestMapping(value = "/inventory/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    List<Inventory> getByName(@PathVariable String name) {
        return itemsRepo.findByNameContaining(name);
    }

    /**
     * @return item(s) by price lte
     */
    @RequestMapping(value = "/inventory/price/{price}", method = RequestMethod.GET)
    @ResponseBody List<Inventory> getByPrice(@PathVariable int price) {
        return itemsRepo.findByPriceLessThanEqual(price);
    }

    /**
     * Add Item
     * @return transaction status
     */
    @RequestMapping(value = "/inventory", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody String create(@RequestBody Inventory payload) {
        try {
            itemsRepo.save(payload);
        }
        catch (Exception ex) {
            return "Error adding item to inventory: " + ex.toString();
        }
        return "Item succesfully added to inventory! (id = " + payload.getId() + ")";
    }


    /**
     * Update Item
     * @return transaction status
     */
    @RequestMapping(value = "/inventory/update/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseBody String update(@PathVariable long id, @RequestBody Inventory payload) {
        try {
            if (itemsRepo.existsById(id)) {
                payload.setId(id);
                itemsRepo.save(payload);
            } else
                return "Item not found, nothing to update.";
        }
        catch (Exception ex) {
            return "Error updating item: " + ex.toString();
        }
        return "Item succesfully updated!";
    }

    /**
     * Delete Item
     * @return transaction status
     */
    @RequestMapping(value = "/inventory/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody String delete(@PathVariable long id) {
        try {
            if (itemsRepo.existsById(id))
                itemsRepo.deleteById(id);
            else
                return "Item not found, nothing to delete.";
        }
        catch (Exception ex) {
            return "Error deleting item:" + ex.toString();
        }
        return "Item succesfully deleted from inventory!";
    }

    private Iterable<Inventory> failGood() {
        // Simply return an empty array
        ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
        return inventoryList;
    }

    /**
     * @return Cirtcuit breaker tripped
     */
    //@HystrixCommand(fallbackMethod="failGood")
    @RequestMapping("/circuitbreaker")
    @ResponseBody
    public String tripCircuitBreaker() {
        System.out.println("Circuitbreaker Service invoked");
        return "";
    }
}

