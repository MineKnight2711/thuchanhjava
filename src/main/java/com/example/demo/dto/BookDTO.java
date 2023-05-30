package com.example.demo.dto;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private Long categoryId;
    public  BookDTO(){

    }
    public BookDTO(Long id, String title, String author, Double price, Long categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
