package com.fges.command;

import com.fges.Command;
import com.fges.ProductItem;
import com.fges.feature.RemoveFeature;
import org.apache.commons.cli.CommandLine;

import java.util.ArrayList;
import java.util.List;

public class RemoveCommand implements Command {
    private ArrayList<ProductItem> listItems;
    private ProductItem itemToRemove;
    private List<String> args;
    private CommandLine cliOptions;

    public RemoveCommand (List<String> args, CommandLine cliOptions, ArrayList<ProductItem> list_items){
        this.args = args;
        this.cliOptions = cliOptions;
        this.listItems = list_items;
//        this.itemToRemove = item_to_remove;

    }

    @Override
    public int verifArgs() {
        String itemName;
        if (args.size() < 2) {
            System.err.println("Missing arguments for remove");
            return 1;
        }
        itemName = args.get(1);
        ProductItem itemToRemove = new ProductItem(itemName, 0, "");
        return 0;
    }

    @Override
    public int execute() {
        if (verifArgs() == 0){
            RemoveFeature removeFeatureInstance = new RemoveFeature();
            return removeFeatureInstance.execute(this.listItems, this.itemToRemove);
        }
        else {
            return 1;
        }
    }
}
