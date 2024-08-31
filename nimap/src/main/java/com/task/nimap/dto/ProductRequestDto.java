package com.task.nimap.dto;

public class ProductRequestDto {
    private String name;
    private String description;
    private double price;
    private Long categoryId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
