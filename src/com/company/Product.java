package com.company;

public class Product {

    public String productName;
    public int productQuantity;

    Product(String productName, int productQuantity){
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

    private void setQuantity(int newQuantity){
        this.productQuantity = newQuantity;
    }

}
