package com.fges.commands;

import com.fges.ProductItem;
import com.fges.features.AddFeature;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

public class AddCommand implements Command{
    private ArrayList<ProductItem> listItems;
    private ProductItem newItem;
    private List<String> positionalArgs;

    public AddCommand (){}

    public boolean valid_parameters(String[] args) {
        Options cliOptions = new Options();
        CommandLineParser parser = new DefaultParser();

        cliOptions.addRequiredOption("s", "source", true, "File containing the grocery list");
        cliOptions.addOption("t", "type", true, "File type");
        cliOptions.addOption("c", "categorie", true, "Categorie of the product");

        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return false;
        }

//        positionalArgs = cmd.getArgList();
        List<String> positionnalArgs = cmd.getArgList();

        if (positionalArgs.size() < 3) {
            System.err.println("Missing arguments for add");
            return false;
        }
        String itemName = positionalArgs.get(1);
        int quantity = Integer.parseInt(positionalArgs.get(2));
        String productCategory = positionalArgs.size() > 3 ? positionalArgs.get(3) : "default";

        this.newItem = new ProductItem(itemName, quantity, productCategory);
//        AddCommand addCommand = new AddCommand(groceryList, newItem);
//         return addCommand.execute();
        return true;
    }

    public void setList(ArrayList<ProductItem> list) {
        this.listItems = list;
    }

    @Override
    public int execute() {
        return 0;
    }

//    public AddCommand(ArrayList<ProductItem> list_items, ProductItem new_item) {
//        this.listItems = list_items;
//        this.newItem = new_item;
//    }



}
