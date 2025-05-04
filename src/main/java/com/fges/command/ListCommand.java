package com.fges.command;

import com.fges.Command;
import com.fges.ProductItem;
import com.fges.feature.ListFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListCommand implements Command {
    private ArrayList<ProductItem> listItems;
    private Map<String, ArrayList<ProductItem>> category_dictionnaires;

    public ListCommand (ArrayList<ProductItem> groceryList){
        this.listItems = groceryList;
    }

    @Override
    public int verifArgs() {
        return 0;
    }

    @Override
    public int execute() {
        ProductItem nullProduct = new ProductItem();
        ListFeature listFeatureInstance = new ListFeature();
        return listFeatureInstance.execute(this.listItems, nullProduct);
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
