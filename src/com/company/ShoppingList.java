package com.company;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ShoppingList {

    private int categoryQuantity;
    private final ArrayList<Category> myList;

    ShoppingList(){
        this.myList = new ArrayList<>();
        this.categoryQuantity = 0;
    }

    public ArrayList<Category> getMyList(){
        return this.myList;
    }

    private boolean isExistent(String categoryName) throws NoSuchElementException {
        for(Category x: this.myList) {
            if(categoryName.equals(x.getCategoryName())){
                return true;
            }
        }
        return false;
    }

    public void addCategory( String myCategory) throws NoSuchElementException {
        if(!isExistent(myCategory)){
            Category temp = new Category(myCategory);
            this.myList.add(temp);
        }
    }

    public void addItemToCategory(String myCategory, String itemName, int itemQuantity) throws NoSuchElementException{
        for( Category x : this.myList) {
            if(x.getCategoryName().equals(myCategory)){
                x.addItemToCategory(itemName, itemQuantity);
            }
        }
    }

    public void deleteCategory( Category myCategory) throws NoSuchElementException{
        if(this.myList.contains(myCategory)){
            this.myList.remove(myCategory);
            this.categoryQuantity--;
        }
    }

    public void deleteAllCategories() throws NoSuchElementException{
            this.myList.clear();
            this.categoryQuantity = 0;
    }

    public void printList() throws NoSuchElementException{

        final String line = "---------------------------------";
        System.out.println(line + "\n       YOUR SHOPPING LIST\n" + line);

        if(this.myList.isEmpty()){
            System.out.println("Shopping list is empty!");
        }
        else{
            int i = 1;
            for(Category x: this.myList){

                System.out.println(i + ". Category ->[" + x.getCategoryName() + "]<- has :  ["+ x.getProductQuantity() + "] products!");
                x.printCategory();
                i++;
            }
            System.out.println(line);
        }
    }


}
