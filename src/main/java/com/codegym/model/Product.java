package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "products_new")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private Double price;
    private String description;
    private String image;

    public Product() {
    }

    public Product(Long id, String name, String code, Double price, String description, String image) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Product(String name, String code, Double price, String description, String image) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
