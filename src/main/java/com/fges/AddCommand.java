package com.fges;

import java.util.ArrayList;

public class AddCommand implements Command{
    private ArrayList<ProductItem> listItems;
    private ProductItem newItem;

    @Override
    public int execute() {
        if (listItems != null){
            listItems.add(newItem);
            System.out.println("Element " + newItem + " bien ajouté à la liste.");
            return 0;
        }
        else {
            System.out.println("err : La liste n'existe pas. Annulation de l'ajout. ");
            return 1;
        }
    }

    public AddCommand (ArrayList<ProductItem> list_items, ProductItem new_item){
        this.listItems = list_items;
        this.newItem = new_item;
    }
}
