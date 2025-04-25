package com.fges;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListCommand implements Command{
    private ArrayList<ProductItem> listItems;
    private ArrayList<ArrayList<ProductItem>> CategoryList;

    public ListCommand (ArrayList<ProductItem> groceryList){
        this.listItems = groceryList;
    }

    int putItemInCategory (ArrayList itemList){

        return 0;
    }

    @Override
    public int execute() {
        for(ProductItem item: listItems) {
            System.out.println(item);
        }
        return 0;
    }
}
