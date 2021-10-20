package com.example.demo.controller;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private UserService service;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        service.registerDefaultUser(user);

        return "register_success";
    }
    @RequestMapping("/cart")
    public String cart(){
        return "cart";
    }

    @RequestMapping("/product/{id}")
    public String product(@PathVariable("id") Product product, Model model){
        model.addAttribute("product", product);
        return "product";
    }
    @RequestMapping("/profile")
    public String profile(){
        return "profile";
    }


//    @GetMapping("/products")
//    public String listUsers(Model model) {
//        List<User> listUsers = service.listAll();
//        model.addAttribute("listUsers", listUsers);
//
//        return "list-products";
//    }
//    @RequestMapping(path = {"/edit", "/edit/{id}"})
//    public String editProductById(Model model, @PathVariable("id") Optional<Long> id)
//            throws RecordNotFoundException {
//        System.out.println("editProductById" + id);
//
//        if (id.isPresent()){
//            Product product = productService.getProductsById(id.get());
//            model.addAttribute("product", product);
//        }else {
//            model.addAttribute("product", new Product());
//        }
//        return "add-edit-product";
//    }
//    @RequestMapping(path = "/delete/{id}")
//    public String deleteProductById(Model model, @PathVariable("id") Long id)
//            throws RecordNotFoundException{
//        System.out.println("deleteProductById"+id);
//
//        productService.deleteProductById(id);
//        return "redirect:/products";
//    }
//    @RequestMapping(path = "/createProduct", method = RequestMethod.POST)
//    public String createOrUpdateProduct(Product product){
//        System.out.println("createOrUpdateProduct");
//        productService.createOrUpdateProduct(product);
//        return "redirect:/products";
//    }
}

