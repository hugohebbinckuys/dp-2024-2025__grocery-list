package com.fges.features;

import com.fges.ProductItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddFeature implements Feature {
    /*private String sourceFile;
    private String fileType; */

    private List<String> positionalArgs;
    private ArrayList<ProductItem> listItems;
    private ProductItem newItem;

    public AddFeature (ArrayList<ProductItem> listItems, ProductItem newItem){
        this.listItems = listItems;
        this.newItem = newItem;
    }

    @Override
    public int execute() {
        if (listItems != null){
            Optional<ProductItem> oldPotentialItem = listItems.stream()
                    .filter(item -> item.getItemName().equalsIgnoreCase(newItem.getItemName()))
                    .findFirst();

            if (oldPotentialItem.isPresent()){
                ProductItem oldItem = oldPotentialItem.get();
                oldItem.setQuantity(oldItem.getQuantity() + newItem.getQuantity());
                System.out.println("L'élément " + newItem + " existe déjà, ajout de " + newItem.getQuantity() + " à " + oldItem);
                return 0;
            }

            // On ajoute uniquement si l’élément n’existe pas déjà
            listItems.add(newItem);
            System.out.println("Élément " + newItem + " bien ajouté à la liste.");
            return 0;
        } else {
            System.out.println("err : La liste n'existe pas. Annulation de l'ajout.");
            return 1;
        }
    }

    /*

    @Override
    public int valid_parameters(String[] args) {
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
            return 1;
        }

        positionalArgs = cmd.getArgList();

        if (positionalArgs.size() < 3) {
            System.err.println("Missing arguments for add");
            return 1;
        }
        String itemName = positionalArgs.get(1);
        int quantity = Integer.parseInt(positionalArgs.get(2));
        String productCategory = positionalArgs.size() > 3 ? positionalArgs.get(3) : "default";

        /* ProductItem newItem = new ProductItem(itemName, quantity, productCategory);
        AddCommand addCommand = new AddCommand(groceryList, newItem);
         return addCommand.execute();
        return 0;
    } */
}
