package com.develogica.other;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;

/**Excel processing class using the POI library. */
public class JExcelTrial {
    public static void main(String[] args) {
//        Map<String, Object[]> data = new TreeMap<>();
//        data.put("1", new Object[] {"ID", "First Name", "Last Name"});
//        data.put("2", new Object[] {1, "Robert", "Martin"});
//        data.put("3", new Object[] {2, "Donald", "Knuth"});
//        data.put("4", new Object[] {5, "Sam", "Newman"});
//        data.put("5", new Object[] {9, "Joshua", "Bloch"});
//        data.put("6", new Object[] {20, "Cay", "Horstman"});

        String fileName = "Employee_info.xlsx";
//        String fileName = "Q-Bank.xlsx";
//        writeToExcel(data, fileName);

        openExcel(fileName);
    }

    private static void openExcel(String fileName) {
        var file = new File(fileName);
        if (file.exists()) {
            if (file.getName().endsWith("xlsx")) {
                readXLSX(file);
            } else if (file.getName().endsWith("xls")) {
                readHLSX(file);
            }
        }
    }

    private static Workbook readXLSX(File file) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            readSheet( workbook.getSheetAt(0) );
            return workbook;
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Workbook readHLSX(File file) {
        try (HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file))) {
            readSheet( workbook.getSheetAt(0) );
            return workbook;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void readSheet(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                processCell(cell);
            }
            System.out.println();
        }
    }



    private static void processCell(Cell cell) {
        switch (cell.getCellType()) {
            case STRING -> {
                var val = cell.getStringCellValue();
                System.out.print('\t' + val);
            } case NUMERIC -> {
                var num = cell.getNumericCellValue();
//                NumberToTextConverter.toText(cell.getNumericCellValue());
                System.out.print(String.valueOf(num) + '\t');
            } default -> throw new IllegalStateException("Unexpected value: " + cell.getCellType());
        }
    }

    /** Writes <code>Map<K, V></code> object to an Excel file.
     * */
    private static void writeToHSSFExcel(Map<String, ? extends Object[]> data, String fileName) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employee Data");

        writeToSheet(data, sheet);

        saveExcel(workbook, fileName);
    }

    private static void saveExcel(Workbook workbook, String fileName) {
        try (var out = Files.newOutputStream(Path.of(fileName))) {
            workbook.write(out);
            var message = "Data successfully written to : " + fileName;
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToSheet(Map<String, ? extends Object[]> data, Sheet sheet) {
        var keySet = data.keySet();
        int rowNum = 0;
        for (String key : keySet) {
            Row row = sheet.createRow(rowNum++);
            Object[] objArr = data.get(key);
            int colNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(colNum++);
                if (obj instanceof String str) {
                    cell.setCellValue(str);
                } else if (obj instanceof Integer integer) {
                    cell.setCellValue(integer);
                }
            }
        }
    }
}
