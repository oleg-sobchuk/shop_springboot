package com.shop.controller;

import com.shop.product.Product;
import com.shop.product.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/")
public class RestProductController {

    @Autowired
    public ProductDao productDao;

    @GetMapping(value = "/productList")
    public List<Product> getAllProducts(){
        return (List<Product>) productDao.findAll();
    }
}
