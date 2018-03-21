package com.shop.controller;

import com.shop.product.Product;
import com.shop.product.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class ProductController {

    @Autowired
    public ProductDao productDao;

//    @ModelAttribute("product")
//    public Product product(){
//        return new Product();
//    }

    @GetMapping(value = "/")
    public String home(){
        return "home";
    }

    @GetMapping(value = "/productList")
    public String productList(Model model){
        model.addAttribute("products", productDao.findAll());
        return "productList";
    }

    @GetMapping(value = "/addProduct")
    public String addProductPage(Model model){
        model.addAttribute("product",new Product());
        return "addProduct";
    }

    @PostMapping(params="add", value = "/addProduct")
    public String addProduct(@ModelAttribute @Valid Product product, Errors errors, Model model){
        if(errors.hasErrors()){
            return "addProduct";
        }
        Date now = new Date();
        product.setDateAdd(now);
        product.setDateUpdate(now);
        product.setOwnerName(getPrincipal());
        productDao.save(product);
        return "redirect:/productInfo/"+product.getId();
    }

    @PostMapping(params="cancel", value = "/addProduct")
    public String addProduct(){
        return "redirect:/productList";
    }

    @GetMapping(value = "/editProduct/{id}")
    public String updateProductPage(@PathVariable Integer id, Model model){
        model.addAttribute("product", productDao.findById(id));
        return "editProduct";
    }

    @PostMapping(params="edit", value = "/editProduct")
    public String updateProduct(@ModelAttribute @Valid Product product, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("product",product);
            return "editProduct";
        }
        product.setDateUpdate(new Date());
        productDao.save(product);
        return "redirect:/productList";
    }

    @PostMapping(params="delete", value = "/editProduct")
    public String deleteEditetProduct(@RequestParam int id){
        return "redirect:/deleteProduct/"+id;
    }

    @PostMapping(params="cancel", value = "/editProduct")
    public String cancelUpdateProduct(){
        return "redirect:/productList";
    }

    @GetMapping(value="/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productDao.deleteById(id);
        return "redirect:/productList";
    }

    @GetMapping(value="/productInfo/{id}")
    public String productPage(@PathVariable Integer id, Model model){
        model.addAttribute("product", productDao.findById(id));
        return "productInfo";
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
