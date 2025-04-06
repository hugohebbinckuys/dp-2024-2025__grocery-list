package com.fges;

import java.util.ArrayList;

public class RemoveCommand implements Command{
    private ArrayList<ProductItem> listItems;
    private ProductItem ItemToRemove;

    public RemoveCommand (ArrayList<ProductItem> list_items, ProductItem item_to_remove){
        this.listItems = list_items;
        this.ItemToRemove = item_to_remove;
    }

    @Override
    public int execute() {
        if (listItems != null){
            if (listItems.contains(ItemToRemove)) {
                listItems.remove(ItemToRemove);
                System.out.println("Element " + ItemToRemove + " supprimé de la liste");
                return 0;
            }
            else {
                System.out.println("err : Element " + ItemToRemove + " introuvable dans la liste. ");
                return 1;
            }
        }
        else {
            System.out.println("err : La liste n'existe pas. Annulation de la suppression. ");
            return 1;
        }
    }
}
