package com.audiophile.audiophile_backend.entity;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;



@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private String category;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "sold_units")
    private Integer soldUnits;

    public Product() {

    }

    public Product(String name, Double price, String category, Integer stock, Integer soldUnits) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.soldUnits = soldUnits;
    }

    // Getters y Setters
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSoldUnits() {
        return soldUnits;
    }

    public void setSoldUnits(Integer soldUnits) {
        this.soldUnits = soldUnits;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                ", soldUnits=" + soldUnits +
                '}';
    }
}
