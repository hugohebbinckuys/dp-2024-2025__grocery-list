package com.fges;

import java.util.ArrayList;
import java.util.Optional;

public class AddCommand implements Command{
    private ArrayList<ProductItem> listItems;
    private ProductItem newItem;

    @Override
    public int execute() {
        if (listItems != null){
            Optional<ProductItem> oldPotentialItem = listItems.stream()
                    .filter(item -> item.getItemName().equalsIgnoreCase(newItem.getItemName()))
                    .findFirst();

            if (oldPotentialItem.isPresent()){
                ProductItem oldItem = oldPotentialItem.get();
                oldItem.setQuantity(oldItem.getQuantity() + newItem.getQuantity());
                System.out.println("L'élément " + newItem + " existe déjà, ajout de " + newItem.getQuantity() + " à " + oldItem);
                return 0;
            }

            // On ajoute uniquement si l’élément n’existe pas déjà
            listItems.add(newItem);
            System.out.println("Élément " + newItem + " bien ajouté à la liste.");
            return 0;
        } else {
            System.out.println("err : La liste n'existe pas. Annulation de l'ajout.");
            return 1;
        }
    }

    public AddCommand (ArrayList<ProductItem> list_items, ProductItem new_item){
        this.listItems = list_items;
        this.newItem = new_item;
    }
}