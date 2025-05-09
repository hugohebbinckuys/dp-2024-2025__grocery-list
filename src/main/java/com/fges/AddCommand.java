package com.fges;

import java.util.ArrayList;
import java.util.Optional;

public class AddCommand implements Command{
    private ArrayList<ProductItem> listItems;
    private ProductItem newItem;

    public AddCommand (ArrayList<ProductItem> list_items, ProductItem new_item){
        this.listItems = list_items;
        this.newItem = new_item;
    }

    @Override
    public int execute() {
        if (listItems != null){
            Optional<ProductItem> oldPotentialItem = listItems.stream().filter(item -> item.getItemName().equalsIgnoreCase(newItem.getItemName())).findFirst();
            if (oldPotentialItem.isPresent()){
                ProductItem oldItem = oldPotentialItem.get();
                oldItem.setQuantity(oldItem.getQuantity() + newItem.getQuantity());
                System.out.println("L'element " + newItem + " existe deja, ajout de " + newItem.getQuantity() + " " + newItem);
                return 0;
            }
            listItems.add(newItem);
            System.out.println("Element " + newItem + " bien ajouté à la liste.");
            return 0;
        }
        else {
            System.out.println("err : La liste n'existe pas. Annulation de l'ajout. ");
            return 1;
        }
    }


}
