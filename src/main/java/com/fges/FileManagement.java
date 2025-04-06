package com.fges;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.fges.Main.OBJECT_MAPPER;

public class FileManagement {
    private String chemin;
    private String fileName;
    private String fileType;

    public FileManagement (String chemin_, String file_type, String file_name){
        this.chemin = chemin_;
        this.fileType = file_type;
        this.fileName = file_name+"."+file_type;
    }

    public ArrayList<ProductItem> loadItems () throws IOException {
        Path filePath = Paths.get(this.fileName);
        ArrayList<ProductItem> groceryList;
        String fileContent;

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);

            groceryList = (ArrayList<ProductItem>) OBJECT_MAPPER.readValue(fileContent, new TypeReference<List<ProductItem>>() {});
            // Cast the list as an ArrayList to ensure its mutability
            // parce que var parsedList = OBJECT_MAPPER.... renvoie une List<ProductItem> pas une ArrayList
        } else {
            groceryList = new ArrayList<ProductItem>();
        }
        return groceryList;
    }

    public void saveItems (ArrayList<ProductItem> listToSave) throws IOException {
        File file = new File(this.fileName);
        OBJECT_MAPPER.writeValue(file, listToSave);
        System.out.println("Liste sauvegardée dans : " + fileName);
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
