package com.fges.FileManagement;

import com.fges.ProductItem;

import java.io.IOException;
import java.util.ArrayList;

public interface DaoInterface{

    ArrayList<ProductItem> loadFile(FileManager file) throws  IOException;
    void saveFile(FileManager file, ArrayList<ProductItem> groceryList) throws IOException;
}
