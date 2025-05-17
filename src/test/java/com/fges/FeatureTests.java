package com.fges;

import com.fges.feature.AddFeature;
import com.fges.feature.InfoFeature;
import com.fges.feature.ListFeature;
import com.fges.feature.RemoveFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FeatureTests {
    private ArrayList<ProductItem> itemList;

    @BeforeEach
    public void setUp() {
        itemList = new ArrayList<>();
        itemList.add(new ProductItem("Lait", 2, "Produits laitiers"));
        itemList.add(new ProductItem("Pain", 1, "Boulangerie"));
    }

    @Test
    public void testAddFeature_addNewItem() {
        AddFeature addFeature = new AddFeature();
        ProductItem newItem = new ProductItem("Fromage", 1, "Produits laitiers");

        int result = addFeature.execute(itemList, newItem);

        assertEquals(0, result);
        assertTrue(itemList.contains(newItem));
        assertEquals(3, itemList.size());
    }

    @Test
    public void testRemoveFeature_existingItem() {
        RemoveFeature removeFeature = new RemoveFeature();
        ProductItem existingItem = new ProductItem("Pain", 1, "Boulangerie");

        int result = removeFeature.execute(itemList, existingItem);

        assertEquals(0, result);
        assertFalse(itemList.contains(existingItem));
    }

    @Test
    public void testListFeature_groupByCategory() {
        ListFeature listFeature = new ListFeature();
        ProductItem anotherItem = new ProductItem("Yaourt", 3, "Produits laitiers");
        itemList.add(anotherItem);

        int result = listFeature.execute(itemList, null);

        assertEquals(0, result);

    }

    @Test
    public void testInfoFeature_systemInfo() {
        InfoFeature infoFeature = new InfoFeature();

        int result = infoFeature.execute(itemList, null);

        assertEquals(0, result);

    }
}
