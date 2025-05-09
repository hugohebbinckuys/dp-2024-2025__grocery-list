package com.fges.FileManagement;

import com.fges.ProductItem;

import java.io.*;
import java.util.ArrayList;

public class DaoCsv implements DaoInterface {

    @Override
    public ArrayList<ProductItem> loadFile(FileManager file) {
        ArrayList<ProductItem> groceryList = new ArrayList<>();
        String cursor;
        boolean isFirstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(file.getChemin()))) {
            while ((cursor = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = cursor.split(",");

                if (values.length >= 3) {
                    try {
                        ProductItem currentItem = new ProductItem(values[0], Integer.valueOf(values[1]), values[2]);
                        groceryList.add(currentItem);
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de format pour la ligne : " + cursor);
                        System.err.println("La quantité doit être un nombre entier valide.");
                    }
                } else {
                    System.err.println("Erreur de format pour la ligne : " + cursor);
                }
            }
            System.out.println("Éléments chargés avec succès ! ");
            for (ProductItem item : groceryList) {
                System.out.println(item.toString());
            }
            return groceryList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveFile(FileManager file, ArrayList<ProductItem> groceryList) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getChemin()))) {
            bw.write("name,quantity,category");
            bw.newLine();

            for (ProductItem item : groceryList) {
                bw.write(item.getItemName() + "," + item.getQuantity() + "," + item.getCategory());
                bw.newLine(); // Ajouter un saut de ligne après chaque élément
            }

            System.out.println("Fichier sauvegardé avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du fichier : " + e.getMessage());
            throw e; // Propager l'exception pour que le Main puisse la gérer
        }
    }
}