package com.fges.web;

import com.fges.FileManagement.DaoCsv;
import com.fges.FileManagement.DaoInterface;
import com.fges.FileManagement.DaoJson;
import com.fges.FileManagement.FileManager;
import com.fges.ProductItem;
import fr.anthonyquere.MyGroceryShop;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleGroceryShop implements MyGroceryShop {
    private final List<WebGroceryItem> groceries = new ArrayList<>();
    private String sourceFile;
    private String fileType;
    private String chemin = "./";

    public SimpleGroceryShop(String sourceFile, String fileType) {
        this.sourceFile = sourceFile;
        this.fileType = fileType;
    }


    public ArrayList<ProductItem> webItemsToProductItems (List<MyGroceryShop.WebGroceryItem> groceries){
        ArrayList<ProductItem> groceryList = new ArrayList<ProductItem>();
        for (MyGroceryShop.WebGroceryItem item : groceries){
            ProductItem newItem = new ProductItem(item.name(), item.quantity(), item.category());
            groceryList.add(newItem);
        }
        return groceryList;
    }

    private void saveFile (ArrayList<ProductItem> groceryList){

        FileManager fileManager = new FileManager(this.chemin, this.fileType, this.sourceFile);
//        FileManager deuxieme = new FileManager()
        DaoInterface jsonDao = new DaoJson();
        DaoInterface csvDao = new DaoCsv();
        if (fileManager != null) {
            System.out.println("\non passe par ici ou quoicoubé\nVoici le type de fichier spécifié : " + this.fileType);
//            if(fileManager.getFileType().equals("csv")){
            if(this.fileType.equals("csv")){
                System.out.println("on passe pas par la ? \n");
                try {
                    System.out.println("tentative de suavgearde");
                    csvDao.saveFile(fileManager,groceryList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(fileManager.getFileType().equals("json")){
                try {
                    jsonDao.saveFile(fileManager,groceryList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public List<WebGroceryItem> getGroceries() {
        return new ArrayList<>(groceries);
    }

    @Override
    public void addGroceryItem(String name, int quantity, String category) {
        System.out.println();
        groceries.add(new WebGroceryItem(name, quantity, category));
        ArrayList<ProductItem> groceryList = new ArrayList<ProductItem>();
        groceryList = webItemsToProductItems(groceries);
        saveFile(groceryList);


    }

    @Override
    public void removeGroceryItem(String name) {
        groceries.removeIf(item -> item.name().equals(name));
        ArrayList<ProductItem> groceryList = new ArrayList<ProductItem>();
        groceryList = webItemsToProductItems(groceries);
        saveFile(groceryList);
    }

    @Override
    public Runtime getRuntime() {
        return new Runtime(
                LocalDate.now(),
                System.getProperty("java.version"),
                System.getProperty("os.name")
        );
    }
}