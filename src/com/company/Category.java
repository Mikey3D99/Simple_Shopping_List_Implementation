package com.company;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Category {

    private String categoryName;
    private int productQuantity;
    private ArrayList<Product> itemList;

    Category(String categoryName){
        this.categoryName = categoryName;
        this.itemList = new ArrayList<>();
        this.productQuantity = 0;
    }

    public String getCategoryName(){
        return this.categoryName;
    }
    public int getProductQuantity(){
        return this.productQuantity;
    }

    public ArrayList<Product> getItemList(){
        return this.itemList;
    }

    public void addItemToCategory(String itemName, int itemQuantity) throws NoSuchElementException {

        Product temp = new Product(itemName, itemQuantity);
        if(!this.itemList.contains(temp)) {

            this.itemList.add(temp);
            this.productQuantity+= temp.productQuantity;
        }
    }

    public void deleteItemFromCategory( Product myItem)throws NoSuchElementException{

        if(this.itemList.contains(myItem)) {

            this.itemList.remove(myItem);
            this.productQuantity-= myItem.productQuantity;
        }
    }

    public void deleteAllItemsFromCategory() throws NoSuchElementException{
        if(!this.itemList.isEmpty()){
            this.itemList.clear();
            this.productQuantity = 0;
        }
    }

    public void printCategory() throws NoSuchElementException{

        if(this.itemList.isEmpty()){
            System.out.println("Category is empty!");
        }
        else{
            for(Product x: this.itemList){
                System.out.println("  * " + x.productName + " x" + x.productQuantity);
            }
        }
    }

}
