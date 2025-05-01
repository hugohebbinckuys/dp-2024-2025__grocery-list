package com.fges.FileManagement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fges.ProductItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaoJson implements DaoInterface {

    @Override
    public ArrayList<ProductItem> loadFile(FileManager file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        try{

            List<ProductItem> groceryList = objectMapper.readValue(file.getFileObject(), new TypeReference<List<ProductItem>>() {});
            return new ArrayList<>(groceryList);

        }catch(IOException e){
            throw e;
        }
    }

    @Override
    public void saveFile(FileManager file, ArrayList<ProductItem> groceryList) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(file.getFileObject(), groceryList);

    }
}




