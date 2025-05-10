package com.fges.command;

import com.fges.Command;
import com.fges.ProductItem;
import com.fges.feature.AddFeature;
import org.apache.commons.cli.CommandLine;

import java.util.ArrayList;
import java.util.List;

public class AddCommand implements Command {
    private List<String> args;
    private CommandLine cliOptions;
    private ArrayList<ProductItem> listItems;
    private ProductItem newItem;

    public AddCommand(List<String> args, CommandLine cliOptions, ArrayList<ProductItem> list_items){
        this.args = args;
        this.cliOptions = cliOptions;
        this.listItems = list_items;
    }

    public int verifArgs(){
        if (this.args.size() <2){
            System.err.println("Missing arg(s) for add comand\n");
            return 1;
        }
        String productName = args.get(1);
        int quantity;
        try {
            quantity = Integer.parseInt(args.get(2)); // conversion en entier
        }
        catch (NumberFormatException e){
            System.err.println("2nd argument must be an integer");
            return 1;
        }
        String category;
        if (this.args.size()>2){
            category = this.args.get(3);
        }
        else {
            category = "default";
        }

        this.newItem = new ProductItem(productName, quantity, category);

        return 0;
    }

    @Override
    public int execute() {
        if (verifArgs() == 0){
            AddFeature addFeatureInstance = new AddFeature();
            return addFeatureInstance.execute(this.listItems, this.newItem);
        }
        else {
            return 1;
        }
    }
}
