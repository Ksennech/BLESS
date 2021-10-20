package com.example.demo.controller;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public String getAllProducts (Model model){
        System.out.println("getAllProducts");
        List<Product> list = productService.getAllProducts();
        model.addAttribute("products", list);
        return "list-products";
    }
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editProductById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException{
        System.out.println("editProductById" + id);

        if (id.isPresent()){
            Product product = productService.getProductsById(id.get());
            model.addAttribute("product", product);
        }else {
            model.addAttribute("product", new Product());
        }
        return "add-edit-product";
    }
    @RequestMapping(path = "/delete/{id}")
    public String deleteProductById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException{
        System.out.println("deleteProductById"+id);

        productService.deleteProductById(id);
        return "redirect:/products";
    }
    @RequestMapping(path = "/createProduct", method = RequestMethod.POST)
    public String createOrUpdateProduct(Product product){
        System.out.println("createOrUpdateProduct");
        productService.createOrUpdateProduct(product);
        return "redirect:/products";
    }
}
