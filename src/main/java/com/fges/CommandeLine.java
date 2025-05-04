package com.fges;

import com.fges.command.AddCommand;
import com.fges.command.InfoCommande;
import com.fges.command.ListCommand;
import com.fges.command.RemoveCommand;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandeLine {
    private String sourceFile;
    private String fileType;
    private String commande;
    private List<String> positionalArgs;
    private CommandLine cmd;

    public int parseCommand(String[] args) {
        Options cliOptions = new Options();
        CommandLineParser parser = new DefaultParser();

        // Ici : -s est obligatoire normalement mais on va gérer en fonction de si c "info" ou pas
        cliOptions.addOption("s", "source", true, "File containing the grocery list");
        cliOptions.addOption("t", "type", true, "File type");
        cliOptions.addOption("c", "categorie", true, "Categorie of the product");

        try {
            this.cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }

        positionalArgs = cmd.getArgList();

        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        this.commande = positionalArgs.get(0);

        // Gestion spéciale : pour info, le -s est pas obligatorie
        if (!Objects.equals(this.commande, "info") && !cmd.hasOption("s")) {
            System.err.println("Missing required option: -s");
            return 1;
        }

        // On récupère les valeurs SI elles existent
        if (cmd.hasOption("s")) {
            this.sourceFile = cmd.getOptionValue("s");
        }
        this.fileType = cmd.getOptionValue("t", "json");

        return 0;
    }

    public int executeCommand(ArrayList<ProductItem> groceryList) {
        String command = positionalArgs.get(0);
        CommandLine cmd;

        switch (command){
            case "add":
                AddCommand addCommand = new AddCommand(positionalArgs, this.cmd, groceryList);
                return addCommand.execute();
            case "remove" :
                RemoveCommand removeCommand = new RemoveCommand(positionalArgs, this.cmd, groceryList);
                return removeCommand.execute();
            case "list" :
                ListCommand listCommand = new ListCommand(groceryList);
                return listCommand.execute();
            case "info" :
                InfoCommande infoCommande = new InfoCommande();
                return infoCommande.execute();
        }

        System.err.println("Unknown command: " + command);
        return 1;
    }

    public String getSourceFile() {
        return sourceFile;
    }
    public String getFileType() {
        return fileType;
    }
    public String getCommande() {
        return commande;
    }

}
