package com.rytesoft.rytewebspringapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name = "items")
public class Inventory {

    // Use generated ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Item name
    @NotNull
    private String name;

    // Item description
    @NotNull
    private String description;

    // Item price
    @NotNull
    private int price;



    // Item img
    @NotNull
    private String img;

    public Inventory() { }

    public Inventory(long id) {
        this.id = id;
    }

    public Inventory(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;


    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int value) {
        this.price = value;
    }


}