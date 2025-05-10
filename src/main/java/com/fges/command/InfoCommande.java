package com.fges.command;

import com.fges.Command;
import com.fges.ProductItem;
import com.fges.feature.InfoFeature;

import java.util.ArrayList;
import java.util.Date;

public class InfoCommande implements Command {
    ArrayList<ProductItem> listItems = null;
    ProductItem nullItem = null;

    @Override
    public int verifArgs() {
        return 0;
    }

    public int execute() {
        InfoFeature infoFeatureInstance = new InfoFeature();
        infoFeatureInstance.execute(this.listItems, this.nullItem);
        return 0;
    }

    public InfoCommande (){
    }

}
