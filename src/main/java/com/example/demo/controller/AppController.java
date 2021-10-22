package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
}

