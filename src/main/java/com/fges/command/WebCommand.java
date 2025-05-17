package com.fges.command;

import com.fges.Command;
import com.fges.ProductItem;
import com.fges.feature.WebFeature;
import org.apache.commons.cli.CommandLine;

import java.util.ArrayList;
import java.util.List;

public class WebCommand implements Command {

    private List<String> args;
    private ArrayList<ProductItem> groceryList;
    private CommandLine cliOptions;
    private String[] port;
    private String sourceFile;
    private String fileType;

    public WebCommand(List<String> args, ArrayList<ProductItem> groceryList, String sourceFile, String fileType){
        this.args = args;
        this.groceryList = groceryList;
        this.sourceFile = sourceFile;
        this.fileType = fileType;
    }

    @Override
    public int verifArgs() {
        if (this.args.size()<2){
            System.err.println("Missing arg(s) for web command\n");
            return 1;
        }
        try{
            int port = Integer.parseInt(args.get(1)); // transfo en int;
            System.out.println("\nargs.get(1) : " + args.get(1) + "\n");
            this.port = new String[1];
            this.port[0] = args.get(1); //on met les args on reste en String orcq in va passer a LainWeb qui doit avoir des Strings.
        }catch (NumberFormatException e){
            System.err.println("Erreur : l'argument fourni n'est pas un entier valide de type 0000");
            return 1;
        }

        return 0;
    }

    @Override
    public int execute() {
        if (verifArgs()==0){
            WebFeature newWebFeature = new WebFeature(this.port, this.sourceFile, this.fileType);
            newWebFeature.execute(this.groceryList, null);
        }
        return 0;
    }
}