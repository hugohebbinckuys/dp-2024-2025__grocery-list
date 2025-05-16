package com.fges.feature;

import com.fges.Feature;
import com.fges.ProductItem;
import com.fges.web.SimpleGroceryShop;
import fr.anthonyquere.GroceryShopServer;

import java.util.ArrayList;

public class WebFeature implements Feature {
    private String[] port;

    public WebFeature(String[] port) {
        this.port = new String[1];
        this.port[0] = port[0];
    }

    @Override
    public int execute(ArrayList<ProductItem> listItems, ProductItem item) {
        com.fges.web.MainWeb.main(this.port);
        GroceryShopServer server = new GroceryShopServer(new SimpleGroceryShop());
//        server.start(this.port);
        return 0;
    }
}