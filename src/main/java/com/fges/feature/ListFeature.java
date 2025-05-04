package com.fges.feature;

import com.fges.Feature;
import com.fges.ProductItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListFeature implements Feature {
    Map<String , ArrayList<ProductItem>> category_dictionnaires;

    int putItemInCategory (ArrayList<ProductItem> itemList){
        Map<String, ArrayList<ProductItem>> category_dictionnaires = new HashMap<>();
        for(ProductItem item: itemList) {
            String category = item.getCategory();
            if (!category_dictionnaires.containsKey(category)) {
                category_dictionnaires.put(category, new ArrayList<>()); // nouvelle liste
            }
            category_dictionnaires.get(category).add(item); // ajout à la bonne liste
        }
        this.category_dictionnaires = category_dictionnaires; // on l'assigne à l'attr de ListFeature pour pouvoir le réutiliser dans la execute.
        return 0;
    }
    @Override
    public int execute(ArrayList<ProductItem> listItems, ProductItem item) {
        if (listItems.isEmpty()){
            System.out.println("\nAucun produit dans la liste.\n");
            return 0;
        }
        putItemInCategory(listItems);
        System.out.println("\n");
        for(String key: category_dictionnaires.keySet()) {
            System.out.println("#"+key + " : " + category_dictionnaires.get(key));
        }
        System.out.println("\n");
        return 0;
    }
}
