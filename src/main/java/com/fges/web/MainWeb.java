package com.fges.web;

import fr.anthonyquere.GroceryShopServer;
import fr.anthonyquere.MyGroceryShop;

public class MainWeb {
    public static void main(int port) {
        MyGroceryShop groceryShop = new SimpleGroceryShop();
        GroceryShopServer server = new GroceryShopServer(groceryShop);
        server.start(port);

        System.out.println("Grocery shop server started at http://localhost:" + port);

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}