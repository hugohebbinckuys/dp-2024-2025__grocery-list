package com.fges.web;

import fr.anthonyquere.GroceryShopServer;
import fr.anthonyquere.MyGroceryShop;

public class MainWeb {
    public static void main(String [] args) {
        MyGroceryShop groceryShop = new SimpleGroceryShop();
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