package com.fges;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

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

    public static void main(String[] args) {

        CommandeLine cmdLine = new CommandeLine();
        int resultParse = cmdLine.parseCommand(args);
        if (resultParse != 0) {
            return; // stop si parse echoue
        }

        FileManagement fileManager = null;
        ArrayList<ProductItem> groceryList = new ArrayList<>();

        if (!"info".equals(cmdLine.getSourceFile())) {
            String fileName = cmdLine.getSourceFile();
            String fileType = cmdLine.getFileType();
            String chemin = "./";

            fileManager = new FileManagement(chemin, fileType, fileName);

            try {
                groceryList = fileManager.loadItems();
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement de la liste : " + e.getMessage());
                return;
            }
        }

        int result = cmdLine.executeCommand(groceryList);

        if (result == 0 && fileManager != null) {
            try {
                fileManager.saveItems(groceryList);
            } catch (IOException e) {
                System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
            }
        } else {
            System.out.println("La commande a échoué. Liste non sauvegardée.");
        }
    }
}