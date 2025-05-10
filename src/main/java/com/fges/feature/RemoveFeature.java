package com.fges.feature;

import com.fges.Feature;
import com.fges.ProductItem;

import java.util.ArrayList;

public class RemoveFeature implements Feature {
    @Override
    public int execute(ArrayList<ProductItem> listItems, ProductItem item) {
        if (listItems != null){
            if (listItems.contains(item)) {
                listItems.remove(item);
                System.out.println("Element " + item + " supprimé de la liste");
                return 0;
            }
            else {
                System.out.println("err : Element " + item + " introuvable dans la liste. ");
                return 1;
            }
        }
        else {
            System.out.println("err : La liste n'existe pas. Annulation de la suppression. ");
            return 1;
        }
    }
}
