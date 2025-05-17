package com.fges;

import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fges.FileManagement.DaoCsv;
import com.fges.FileManagement.DaoJson;
import com.fges.FileManagement.FileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class FileManagementTests {

    private ArrayList<ProductItem> products;
    private ArrayList<ProductItem> emptyproducts;

    @BeforeEach
    public void setUp() {
        products = new ArrayList<>();
        products.add(new ProductItem("Lait", 2, "Produits laitiers"));
        products.add(new ProductItem("Pain", 1, "Boulangerie"));

        emptyproducts = new ArrayList<>();
    }

    @Test
    public void testSaveFileJsonFormat() throws IOException{



        File tempfile = Files.createTempFile("test_json",".json").toFile();
        FileManager fileManager = new FileManager(tempfile.getAbsolutePath(),"json","test_json");

        DaoJson jsonDao = new DaoJson();
        jsonDao.saveFile(fileManager, products);

        assertTrue(tempfile.exists());

    }

    @Test
    public void testSaveFileCsvFormat() throws IOException{

        File tempfile = Files.createTempFile("test_csv",".csv").toFile();
        FileManager fileManager = new FileManager(tempfile.getAbsolutePath(),"csv","test_csv");

        DaoCsv csvDao = new DaoCsv();
        csvDao.saveFile(fileManager,products);

        assertTrue(tempfile.exists());

    }

    @Test
    public void testLoadFileJsonFormat() throws  IOException{
        File tempfile = Files.createTempFile("test_json",".json").toFile();
        FileManager fileManager = new FileManager(tempfile.getAbsolutePath(),"json","test_json");
        DaoJson jsonDao = new DaoJson();
        jsonDao.saveFile(fileManager, products);

        ArrayList<ProductItem> loadedProducts = jsonDao.loadFile(fileManager);
        assertTrue(loadedProducts.size() == products.size());
        assertTrue(loadedProducts.get(0).getItemName().equals("Lait"));
        assertTrue(loadedProducts.get(1).getItemName().equals("Pain"));


    }

    @Test
    public void testLoadFileCsvFormat() throws IOException{
        File tempfile = Files.createTempFile("test_json",".json").toFile();
        FileManager fileManager = new FileManager(tempfile.getAbsolutePath(),"json","test_json");
        DaoCsv csvDao = new DaoCsv();
        csvDao.saveFile(fileManager, products);

        ArrayList<ProductItem> loadedProducts = csvDao.loadFile(fileManager);
        assertTrue(loadedProducts.size() == products.size());
        assertTrue(loadedProducts.get(0).getItemName().equals("Lait"));
        assertTrue(loadedProducts.get(1).getItemName().equals("Pain"));

    }
}
