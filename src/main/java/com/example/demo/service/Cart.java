package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<CartItem> items;
    private double total;

    public Cart() {
        items = new ArrayList<>();
        total = 0;
    }

    public CartItem getItem(Product product){
        for (CartItem item : items){
            if(item.getProduct().getId() == product.getId()){
                return item;
            }
        }
        return null;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getItemCount(){
        return items.size();
    }

    public void addItem(CartItem item){
        addItem(item.getProduct(), item.getQuantity());
    }

    public void addItem(Product product, int quantity){
        CartItem item = getItem(product);

        if (item != null){
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            item = new CartItem(product);
            item.setQuantity(quantity);
            items.add(item);
        }
    }

    public void updateItem(Product product, int quantity) {
        CartItem item = getItem(product);

        if (item != null) {
            item.setQuantity(quantity);
        } else {
        }
    }
    public void removeItem(Product product){
        CartItem item = getItem(product);

        if (item != null){
            items.remove(item);
        } else {
        }
    }

    public void clear(){
        items.clear();
        total = 0;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public double getTotal(){
        total = 0;
        for (CartItem item : items) {
            total += item.getSubTotal();
        }
        return total;
    }
}
