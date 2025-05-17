// hugo


package com.fges;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fges.FileManagement.DaoCsv;
import com.fges.FileManagement.DaoInterface;
import com.fges.FileManagement.DaoJson;
import com.fges.FileManagement.FileManager;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import fr.anthonyquere.GroceryShopServer;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        CommandeLine cmdLine = new CommandeLine();
        int resultParse = cmdLine.parseCommand(args);
        if (resultParse != 0) {
            return; // stop si parse echoue
        }
        String command;
        command = cmdLine.getCommande();
//        System.out.println("Commande lancée : " + command) debug

        FileManager fileManager = null;
        ArrayList<ProductItem> groceryList = new ArrayList<>();

        DaoInterface jsonDao = new DaoJson(); // object pour la lecture / ecriture de fichier json
        DaoInterface csvDao = new DaoCsv(); // object pour la lecture / ecriture de fichier csv

        if (!command.equals("info")) {
            String fileName = cmdLine.getSourceFile();
            String fileType = cmdLine.getFileType();
            String chemin = "./";

            fileManager = new FileManager(chemin, fileType, fileName);

            File file = new File(fileManager.getChemin());
            if (file.exists()) {
                // Le fichier existe, on le charge
                if(fileType.equals("csv")){
                    groceryList = csvDao.loadFile(fileManager);
                }
                else if(fileType.equals("json")){
                    groceryList = jsonDao.loadFile(fileManager);
                }
            } else {
                // Le fichier n'existe pas, on continue avec une liste vide
                System.out.println("Création d'un nouveau fichier : " + fileManager.getChemin());
            }
        }

        int result = cmdLine.executeCommand(groceryList);

        if (command.equals("info") || command.equals("list")){
            return; // si on a fait la commande info ou list on a pas besoin de sauvegarder (donc pas besoin d'aller plus loin)
        }

        if (result == 0 && fileManager != null) {
            if(fileManager.getFileType().equals("csv")){
                csvDao.saveFile(fileManager,groceryList);
            }
            else if(fileManager.getFileType().equals("json")){
                jsonDao.saveFile(fileManager,groceryList);
            }
        }
    }
}