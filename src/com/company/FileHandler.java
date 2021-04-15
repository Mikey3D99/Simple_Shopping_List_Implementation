package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileHandler {

    private final String FILE_NAME;
    private final String IS_SEP;
    private boolean isSaved;
    private boolean isValid;


    FileHandler(String fileName){
        this.FILE_NAME = fileName;
        this.IS_SEP = "--------";
        this.isSaved = false;
        this.isValid = false;
    }

    private static boolean isStringUppercase(String str){
        if(str.isBlank() || str.isEmpty()){
            return false;
        }

        char[] charArray = str.toCharArray(); //convert str to a char array

        for( char x: charArray){  ///traverse through it
            if(!java.lang.Character.isUpperCase(x)){
                return false;
            }
        }
        return true;
    }


    private boolean validateFile() throws NoSuchElementException, FileNotFoundException {

        if(!this.FILE_NAME.contains(".txt")){
            throw new FileNotFoundException("The file is not a text file!");
        }

        File myFile = new File(this.FILE_NAME);
        Scanner myReader = new Scanner(myFile);
        int checkCategories = 0;
        int checkItems = 0;
        int checkSeparators = 0;

        while(myReader.hasNextLine()){

            String temp = myReader.nextLine();
            if ( isStringUppercase(temp) && !myReader.hasNextLine()) {
                throw new FileNotFoundException("Correct file not found! No items found!");
            }

            if(isStringUppercase(temp))  {  //if it is a category, check if the next lines are lowercase
                checkCategories++;
            }
            else if(temp.equals(this.IS_SEP)){
                checkSeparators++;
            }
            else if(temp.matches("^[a-z0-9-]*$") ){ //accepts '-', 0-9 and lowercase letters
                checkItems++;
            }
        }

        if(checkSeparators != checkCategories || checkCategories > checkItems){
            System.out.println("Correct file not found! Incorrect number of items, categories or separators!");
            throw new FileNotFoundException();
        }
        else if(checkSeparators == 0 || checkItems == 0){//empty file/ no separators/ no categories
            System.out.println("Correct file not found! Empty file!");
            throw new FileNotFoundException();
        }

        myReader.close();

        return true;
    }

    public void readListFile(ShoppingList myList) throws NoSuchElementException, FileNotFoundException {

        try{
            validateFile();
            File myFile = new File(FILE_NAME);
            Scanner myReader = new Scanner(myFile);

            String tempCategory = null;

            while(myReader.hasNextLine()){

                String temp = myReader.nextLine();

                if(isStringUppercase(temp) && myReader.hasNextLine()){
                    tempCategory = temp;
                    myList.addCategory(temp);
                }
                else if(!temp.equals(IS_SEP) && !isStringUppercase(temp)){
                    myList.addItemToCategory(tempCategory, temp, 1);
                }
                else{
                    System.out.println(temp);
                }
            }
            myReader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found!");
            throw new FileNotFoundException();
        }
        catch(NoSuchElementException e){
            System.out.println("There was no next line!");
            throw new NoSuchElementException();
        }
    }


    private void saveCategory( Category toSave, FileWriter writer) throws NoSuchElementException, IOException {
        for(Product x: toSave.getItemList()){
            writer.write(x.productName + " " + x.productQuantity +'\n');
        }
    }

    public void saveList (ShoppingList myList, String fileName) throws NoSuchElementException, IOException {

        try{
            FileWriter myWriter = new FileWriter(fileName);

            for(Category x: myList.getMyList()){
                myWriter.write(x.getCategoryName() + '\n');
                saveCategory(x, myWriter);
                myWriter.write(this.IS_SEP + '\n');
            }
            myWriter.close();
        }
        catch (NoSuchElementException e){
            throw new NoSuchElementException("There was no Category!");
        }
        catch( IOException ex){
            throw new IOException("Problem with writer, while saving the list!");
        }
    }
}
