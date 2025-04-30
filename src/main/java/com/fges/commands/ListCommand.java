package com.fges.commands;

import com.fges.ProductItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListCommand implements Command{
    private ArrayList<ProductItem> listItems;
    private Map<String, ArrayList<ProductItem>> category_dictionnaires;

    public ListCommand (ArrayList<ProductItem> groceryList){
        this.listItems = groceryList;
    }

    int putItemInCategory (ArrayList<ProductItem> itemList){
        Map<String, ArrayList<ProductItem>> category_dictionnaires = new HashMap<>();
        for(ProductItem item: itemList) {
            String category = item.getCategory();
            if (!category_dictionnaires.containsKey(category)) {
                category_dictionnaires.put(category, new ArrayList<>());
            }
            category_dictionnaires.get(category).add(item);
        }
        this.category_dictionnaires = category_dictionnaires;
        return 0;
    }


    @Override
    public int execute() {
        if (listItems.isEmpty()){
            System.out.println("\nAucun produit dans la liste.\n");
            return 0;
        }
        putItemInCategory(listItems);
        for(String key: category_dictionnaires.keySet()) {
            System.out.println("\n#"+key + " : " + category_dictionnaires.get(key));
        }
        System.out.println("\n");
        return 0;
    }

    public ArrayList<ProductItem> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<ProductItem> listItems) {
        this.listItems = listItems;
    }

    public Map<String, ArrayList<ProductItem>> getCategory_dictionnaires() {
        return category_dictionnaires;
    }

    public void setCategory_dictionnaires(Map<String, ArrayList<ProductItem>> category_dictionnaires) {
        this.category_dictionnaires = category_dictionnaires;
    }

}
