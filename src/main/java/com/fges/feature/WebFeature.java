package com.fges.feature;

import com.fges.Feature;
import com.fges.ProductItem;
import fr.anthonyquere.GroceryShopServer;

import java.util.ArrayList;

public class WebFeature implements Feature {
    private int port;

    public WebFeature(int port) {
        this.port = port;
    }

    @Override
    public int execute(ArrayList<ProductItem> listItems, ProductItem item) {
        fr.anthonyquere.MainWeb.run
        GroceryShopServer server = new GroceryShopServer(new SimpleGroceryShop());
        server.start(this.port);
        return 0;
    }
}
