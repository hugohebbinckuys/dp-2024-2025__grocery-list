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

/*
public class Main {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
// ObjectMapper = utilisé pour convertir objets java en JSON

    public static void main(String[] args) throws IOException {
        System.exit(exec(args));
    }

     public static int exec(String[] args) throws IOException {
        // Setup CLI interface
        Options cliOptions = new Options();
        CommandLineParser parser = new DefaultParser(); // parser = pour analyser la ligne de commande

        cliOptions.addRequiredOption("s", "source", true, "File containing the grocery list");
        cliOptions.addOption("t", "type", true, "File type");

        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args); // ici c pour comparer entre les arg passés et les arguments obligé
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");
        String TypeFile = cmd.getOptionValue("t");

        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);

        // Load current grocery list state

        //Path filePath = Paths.get(fileName);

        String fileContent = "";

        List<String> groceryList;

        if (TypeFile == null){
            fileName = fileName +".json";
        }
        else {
            fileName = fileName + "." + TypeFile;
        }
        Path filePath = Paths.get(fileName);
        System.out.println("nom du filename final : " + fileName);

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);

            var parsedList = OBJECT_MAPPER.readValue(fileContent, new TypeReference<List<String>>() {
            });
            // Cast the list as an ArrayList to ensure its mutability
            groceryList = new ArrayList<>(parsedList);
        } else {
            groceryList = new ArrayList<>();
        }

        // interpret command

        switch (command) {
            case "add" -> {
                if (positionalArgs.size() < 3) {
                    System.err.println("Missing arguments");
                    return 1;
                }

                String itemName = positionalArgs.get(1);
                int quantity = Integer.parseInt(positionalArgs.get(2));

                groceryList.add(itemName + ": " + quantity);

                var outputFile = new File(fileName);

                OBJECT_MAPPER.writeValue(outputFile, groceryList);
                return 0;
            }
            case "list" -> {
                for (String item : groceryList) {
                    System.out.println(item);
                }
                return 0;
            }
            case "remove" -> {
                if (positionalArgs.size() < 2) {
                    System.err.println("Missing arguments");
                    return 1;
                }

                String itemName = positionalArgs.get(1);
                var newGroceryList = groceryList.stream()
                        .filter(item -> !item.contains(itemName))
                        .toList();

                var outputFile = new File(fileName);

                OBJECT_MAPPER.writeValue(outputFile, newGroceryList);
                return 0;
            }

            default -> throw new IllegalArgumentException("Unknown command: " + command);
        }
    }
}
*/

public class Main {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {

        CommandeLine cmdLine = new CommandeLine();

        FileManagement fileManager;
        ArrayList<ProductItem> groceryList;

        int resultParse;
        try {
            resultParse = cmdLine.createCommand(args, new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Erreur lors du parsing : " + e.getMessage());
            return;
        }

        String fileName = cmdLine.getSourceFile();
        String fileType = cmdLine.getFileType();
        String chemin = "./";

        fileManager = new FileManagement(chemin, fileType, fileName);

        try{
            groceryList = fileManager.loadItems();
        }catch (IOException e){
            System.out.println("err lors du chrmeent de la liste : "+ e.getMessage());
            return;
        }

        int result = cmdLine.createCommand(args, groceryList);

        if (result == 0) {
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

