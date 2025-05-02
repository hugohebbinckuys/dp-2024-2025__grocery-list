package com.fges;

import java.util.Date;

public class InfoCommande implements Command{
    public int execute() {
        System.out.println("\n==> Appel de InfoCommande.execute()\n");
        Date ajd = new Date();
        String os = System.getProperty("os.name");
        String javaV = System.getProperty("java.version");
        System.out.println("Today's date: " + ajd + "\nOperating System:" + os + "\nJava version: " + javaV);
        return 0;
    }

    @Override
    public int verifArgs() {
        return 0;
    }

    public InfoCommande (){
    }

}
