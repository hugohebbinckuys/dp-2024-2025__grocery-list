package com.fges.web;

import com.fges.ProductItem;
import fr.anthonyquere.GroceryShopServer;
import fr.anthonyquere.MyGroceryShop;

import java.util.ArrayList;
import java.util.List;

public class MainWeb {
    private ArrayList<ProductItem> groceryList;
    private String sourceFile;
    private String fileType;

    public MainWeb(ArrayList<ProductItem> groceryList, String sourceFile, String fileType) {
        this.groceryList = groceryList;
        this.sourceFile = sourceFile;
        this.fileType = fileType;
    }

    public MyGroceryShop add_all_items (ArrayList<ProductItem> groceryList){
        MyGroceryShop groceryShop = new SimpleGroceryShop(this.sourceFile, this.fileType);
        for (ProductItem product : groceryList){
            groceryShop.addGroceryItem(product.getItemName(), product.getQuantity(), product.getCategory());
        }
        return groceryShop;
    }


    public void main(String [] args) {
        MyGroceryShop groceryShop = new SimpleGroceryShop(this.sourceFile, this.fileType);

        ArrayList<ProductItem> groceryList = this.groceryList;
        groceryShop = add_all_items(groceryList);

        GroceryShopServer server = new GroceryShopServer(groceryShop);
        int port = Integer.parseInt(args[0]);

        server.start(port);

        System.out.println("Grocery shop server started at http://localhost:" + port);

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}