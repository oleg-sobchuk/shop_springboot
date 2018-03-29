package com.shop.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="products")
public class Product {

    public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    @Id
    @GeneratedValue
    @Column(name="product_id")
    private int id;

    @NotNull
    @Size(min=2, max=35)
    @Column(name="product_name")
    private String name;

    @Column(name="owner_name")
    private String ownerName;

    @NotNull
    @Size(min=5, max=255)
    @Column(name="description")
    private String desc;

    @DateTimeFormat(pattern = DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @Column(name="date_add")
    private Date dateAdd;

    @DateTimeFormat(pattern = DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @Column(name="date_update")
    private Date dateUpdate;

    public Product(){
    }

    public Product(String name, String desc) {
        this.name = name;
        this.desc = desc;
        Date now = new Date();
        dateAdd = now;
        dateUpdate = now;
        ownerName = "user_name";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
