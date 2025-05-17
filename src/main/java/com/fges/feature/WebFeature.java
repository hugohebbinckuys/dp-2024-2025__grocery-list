package com.fges.feature;

import com.fges.Feature;
import com.fges.ProductItem;
import com.fges.web.SimpleGroceryShop;
import com.fges.web.MainWeb;
import fr.anthonyquere.GroceryShopServer;

import java.util.ArrayList;

public class WebFeature implements Feature {
    private String[] args_to_send;
    private String sourceFile;
    private String fileType;

    public WebFeature(String[] port, String sourceFile, String fileType) {
        this.args_to_send = new String[2];
        this.args_to_send[0] = port[0];
        this.sourceFile = sourceFile;
        this.fileType = fileType;
    }

    @Override
    public int execute(ArrayList<ProductItem> listItems, ProductItem item) {
        MainWeb mainWeb = new MainWeb(listItems, sourceFile, fileType);
        mainWeb.main(this.args_to_send);
//        com.fges.web.MainWeb.main(this.args_to_send);
//        GroceryShopServer server = new GroceryShopServer(new SimpleGroceryShop());
//        server.start(this.port);
        return 0;
    }
}