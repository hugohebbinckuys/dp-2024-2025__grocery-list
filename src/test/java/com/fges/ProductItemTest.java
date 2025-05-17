package com.fges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductItemTest {
    private List<ProductItem> products;

    @BeforeEach
    public void setUp() {
        products = new ArrayList<>();
        products.add(new ProductItem("Lait", 2, "Produits laitiers"));
        products.add(new ProductItem("Pain", 1, "Boulangerie"));
        products.add(new ProductItem("Pommes", 5, "Fruits"));
        products.add(new ProductItem("Poulet", 1, "Viande"));
        products.add(new ProductItem("Chocolat", 4, "Confiserie"));
    }
    @Test
    public void itemQuantityIsValid(){
        for (ProductItem product : products ){
            assertTrue(product.getQuantity() > 0);
        }
    }

    @Test
    public void itemNameIsValid(){
        for(ProductItem product: products){
            assertNotNull(product.getItemName());
            assertFalse(product.getItemName().isEmpty());
        }
    }
    @Test
    public void itemCategoryIsValid(){
        for(ProductItem product: products){
            assertNotNull(product.getCategory());
            assertFalse(product.getCategory().isEmpty());
        }
    }

}

