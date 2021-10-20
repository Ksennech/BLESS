package com.example.demo.service;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        System.out.println("getAllProducts");
        List<Product> result = (List<Product>) productRepository.findAll();

        if(result.size()>0){
            return result;
        } else {
            return new ArrayList<Product>();
        }
    }
    public Product getProductsById(Long id) throws RecordNotFoundException {
        System.out.println("getProductsById");
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RecordNotFoundException("not products found");
        }
    }
    public Product createOrUpdateProduct (Product product){
        System.out.println("createOrUpdateProduct");
        product = productRepository.save(product);
        Optional<Product> product1 = productRepository.findById(product.getId());

        if (product1.isPresent()){
            Product newProduct = product1.get();
            newProduct.setProdName(product.getProdName());
            newProduct.setProdDescription(product.getProdDescription());
            newProduct.setPrice(product.getPrice());

            newProduct = productRepository.save(product);
            return newProduct;
        }else{
            product = productRepository.save(product);
            return product;
        }
    }
    public void deleteProductById (Long id) throws RecordNotFoundException {
        System.out.println("deleteProductById");
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()){
            productRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException ("no product found");
        }
    }
}
