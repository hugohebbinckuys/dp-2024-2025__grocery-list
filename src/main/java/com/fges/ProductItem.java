package com.fges;

import java.util.ArrayList;

public class ProductItem {
    private String itemName;
    private Integer quantity;
    private String category;

    public ProductItem(){}

    // def d'un constructeur poyr que ce soit + facile à créer
    public ProductItem (String name, Integer qty, String category){
        this.itemName = name;
        this.quantity = qty;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item (" + this.itemName + " " +  this.quantity + " " + this.category + ")";
    }

    // on doit surcharger la méthode equals precq pour l'instant quand on utilise "contains" dans removeItem p/ex => pour tester si des elements sont deja existants on compare le pointeur mais 2 elements "Pomme" n'ont pas le même pointeur donc on aura jamais contains == 1

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        else {
            if (obj == null || this.getClass() != obj.getClass()){
                return false;
            }
            ProductItem item = (ProductItem)obj;
            if (item.itemName.equals(this.itemName)){
                return true;
            }
            return false;
        }
    }
}
