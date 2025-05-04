package com.fges.feature;

import com.fges.Feature;
import com.fges.ProductItem;

import java.util.ArrayList;
import java.util.Date;

public class InfoFeature implements Feature {

    int verifArgs() {
        return 0;
    }

@Override
    public int execute(ArrayList<ProductItem> listItems, ProductItem item) {
        Date ajd = new Date();
        String os = System.getProperty("os.name");
        String javaV = System.getProperty("java.version");
        System.out.println("\nToday's date: " + ajd + "\nOperating System:" + os + "\nJava version: " + javaV + "\n");
        return 0;
    }
}
