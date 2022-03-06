package com.invoprep.model;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static void main(String[] args) {

    }


    public static List<Invoice> getInvoiceList(Sheet sheet) {
        return null;
    }

    public static List<Invoice> getCompleteInvoice(Sheet invoiceList, Sheet itemList) {
        var invoices = getInvoiceList(invoiceList);


        return null;
    }

    public static List<Invoice> fillItems(Sheet itemSheet) {

        return fillItems(new ArrayList<Invoice>(), itemSheet);
    }

    private static List<Invoice> fillItems(ArrayList<Invoice> invoices, Sheet itemSheet) {
        return null;
    }
}
