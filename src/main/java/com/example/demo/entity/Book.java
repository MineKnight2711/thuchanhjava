package com.example.demo.entity;

import com.example.demo.validator.annotation.ValidCategoryId;
import com.example.demo.validator.annotation.ValidUserID;
import com.example.demo.validator.annotation.ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.websocket.OnError;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Data

@Entity(name = "book")
@Table (name = "book")
public class Book{
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")

    @NotEmpty(message = "Tiêu đề không được trống")
    @Size(max=50,min=1,message = "Tiều đề phải ít hơn 50 ký tự")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "price")
    @NotNull(message = "Giá không được trống")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    @ValidCategoryId
    private Category categories;


    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "id")
    @ValidUserID
    private User users;
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

    public Category getCategory() {
        return categories;
    }

    public void setCategory(Category category) {
        this.categories = category;
    }
}
