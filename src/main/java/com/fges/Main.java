package com.fges;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fges.FileManagement.DaoCsv;
import com.fges.FileManagement.DaoInterface;
import com.fges.FileManagement.DaoJson;
import com.fges.FileManagement.FileManager;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        CommandeLine cmdLine = new CommandeLine();
        int resultParse = cmdLine.parseCommand(args);
        if (resultParse != 0) {
            return; // stop si parse echoue
        }

        FileManager fileManager = null;
        ArrayList<ProductItem> groceryList = new ArrayList<>();

        DaoInterface jsonDao = new DaoJson(); // object pour la lecture / ecriture de fichier json
        DaoInterface csvDao = new DaoCsv(); // object pour la lecture / ecriture de fichier csv

        if (!"info".equals(cmdLine.getSourceFile())) {
            String fileName = cmdLine.getSourceFile();
            String fileType = cmdLine.getFileType();
            String chemin = "./";

            fileManager = new FileManager(chemin, fileType, fileName);

            if(fileType.equals("csv")){
                groceryList = csvDao.loadFile(fileManager);
            }
            else if(fileType.equals("json")){
                groceryList = jsonDao.loadFile(fileManager);
            }

        }

        int result = cmdLine.executeCommand(groceryList);
        if(result == 0 && fileManager != null){
            if(fileManager.getFileType().equals("csv")){
                csvDao.saveFile(fileManager,groceryList);
            }
            else if(fileManager.getFileType().equals("json")){
                csvDao.saveFile(fileManager,groceryList);
            }

        }




    }
}
