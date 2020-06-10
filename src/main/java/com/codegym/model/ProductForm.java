package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private Long id;
    private String name;
    private String code;
    private Double price;
    private String description;
    private MultipartFile image;

    public ProductForm() {
    }

    public ProductForm(Long id, String name, String code, Double price, String description, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public ProductForm(String name, String code, Double price, String description, MultipartFile image) {
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
