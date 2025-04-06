package com.fges;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandeLine {
    private String sourceFile;
    private String fileType;
    private String productCategorie;

    public String getSourceFile() {
        return sourceFile;
    }
    public String getFileType() {
        return fileType;
    }

    int createCommand (String[] args, ArrayList<ProductItem> groceryList){
        Options cliOptions = new Options();
        CommandLineParser parser = new DefaultParser(); // parser = pour analyser la ligne de commande

        cliOptions.addRequiredOption("s", "source", true, "File containing the grocery list");
        cliOptions.addOption("t", "type", true, "File type");
        cliOptions.addOption("c", "categorie", true, "Categorie of the product");

        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args); // ici c pour comparer entre les arg passés et les arguments obligé
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }

        this.sourceFile = cmd.getOptionValue("s");
        this.fileType = cmd.getOptionValue("t", "json");
        this.productCategorie = cmd.getOptionValue("c", "default");

        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);

        if (Objects.equals(command, "add")) {
            if (positionalArgs.size() < 3) {
                System.err.println("Missing arguments");
                return 1;
            }
            String itemName = positionalArgs.get(1);
            int quantity = Integer.parseInt(positionalArgs.get(2));
            ProductItem newItem = new ProductItem(itemName, quantity);
            AddCommand addCommande = new AddCommand(groceryList, newItem);
            return addCommande.execute();
        }

        if (Objects.equals(command, "remove")){
            if (positionalArgs.size() < 2) {
                System.err.println("Missing arguments");
                return 1;
            }
            String itemName = positionalArgs.get(1);
            ProductItem itemToRemove = new ProductItem(itemName, 0);
            RemoveCommand removeCommand = new RemoveCommand(groceryList, itemToRemove);
            return removeCommand.execute();
        }

    return 0;
    }


}
