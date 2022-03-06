package com.invoprep.model;

import java.util.List;

class Item {
    String name;
    int quantity;
    int unitCost;

    public double getTotalCost() {
        return unitCost * quantity;
    }
}

public class Invoice {
    private List<Item> itemList;
    private double total;
}

class PurchaseInvoice extends Invoice {

}

