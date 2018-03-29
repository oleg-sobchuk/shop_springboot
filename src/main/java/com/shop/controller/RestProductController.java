package com.shop.controller;

import com.shop.product.Product;
import com.shop.product.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/")
public class RestProductController {

    @Autowired
    public ProductDao productDao;

    @GetMapping(value = "productList")
    public List<Product> getAllProducts(){
        return (List<Product>) productDao.findAll();
    }

    @PostMapping(value = "addProduct", consumes = "application/json")
    public Product addProduct(@RequestBody Product product){
        Date now = new Date();
        product.setDateAdd(now);
        product.setDateUpdate(now);
        product.setOwnerName(getPrincipal());
        productDao.save(product);
        return product;
    }

    @GetMapping(value = "findProduct/{id}")
    public Optional<Product> editProduct(@PathVariable Integer id){
        return productDao.findById(id);
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            userName = ((UserDetails)principal).getUsername();
        }else{
            userName = principal.toString();
        }
        return userName;
    }
}
