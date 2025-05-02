package com.fges.feature;

import com.fges.ProductItem;

import java.util.ArrayList;
import java.util.Optional;

public class AddFeature {

    public int addToList (ArrayList<ProductItem> listItems, ProductItem newItem){
        if (!listItems.isEmpty()) {
            Optional<ProductItem> oldPotentialItem = listItems.stream()
                    .filter(item -> item.getItemName().equalsIgnoreCase(newItem.getItemName()))
                    .findFirst();

            if (oldPotentialItem.isPresent()) {
                ProductItem oldItem = oldPotentialItem.get();
                oldItem.setQuantity(oldItem.getQuantity() + newItem.getQuantity());
                System.out.println("L'élément " + newItem + " existe déjà, ajout de " + newItem.getQuantity() + " à " + oldItem);
                return 0;
            }
        }
        // On ajoute uniquement si l’élément n’existe pas déjà
        listItems.add(newItem);
        System.out.println("Élément " + newItem + " bien ajouté à la liste.");
        return 0;
    }
}
