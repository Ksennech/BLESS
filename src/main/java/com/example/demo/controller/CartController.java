package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.Cart;
import com.example.demo.service.CartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartManager cartManager;

    @RequestMapping("/add")
    public String add(HttpSession session, @RequestParam("id") Product product,
                      @RequestParam(value = "qty", required = false, defaultValue = "1") int qty){
        Cart cart = cartManager.getCart(session);
        cart.addItem(product, qty);
        return "cart";
    }

    @RequestMapping("/remove")
    public String remove(HttpSession session, @RequestParam("id") Product product){
        Cart cart = cartManager.getCart(session);
        cart.removeItem(product);
        return "cart";
    }

    @RequestMapping("/update")
    public String update(HttpSession session, @RequestParam("id") Product product, @RequestParam("qty") int qty){
        Cart cart = cartManager.getCart(session);
        cart.updateItem(product, qty);
        return "cart";
    }
}


