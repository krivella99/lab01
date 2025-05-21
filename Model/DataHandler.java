package Model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {
    List<List<Double>> datasets;
    Map<String, List<Double>> results;
    Map<String, Map<Integer, Map<Integer, Double>>> pairwiseResults;
    double confidenceLevel = 0.95;

    public DataHandler() {
        datasets = new ArrayList<>();
        results = new HashMap<>();
        pairwiseResults = new HashMap<>();
    }

    public void loadData(String filePath) throws IOException {

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                List<Double> dataset = new ArrayList<>();

                for (Row row : sheet) {
                    for (Cell cell : row) {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            dataset.add(cell.getNumericCellValue());
                        } else if (cell.getCellType() == CellType.STRING) {
                            try {
                                dataset.add(Double.parseDouble(cell.getStringCellValue()));
                            } catch (NumberFormatException e) {

                            }
                        }
                    }
                }


            }
        }

        if (datasets.isEmpty()) {
            throw new IOException("No numbers in file.");
        }
    }


    public void exportResults(String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet statsSheet = workbook.createSheet("Statistics");


            Row headerRow = statsSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Statistic");

            for (int i = 0; i < datasets.size(); i++) {
                headerRow.createCell(i + 1).setCellValue("Dataset " + (i + 1));
            }


            int rowIndex = 1;

            String[] statsOrder = {
                    "Sample Size", "Arithmetic Mean", "Geometric Mean", "Variance",
                    "Standard Deviation", "Range", "Minimum", "Maximum",
                    "Coefficient of Variation", "Confidence Interval Lower", "Confidence Interval Upper"
            };

            for (String stat : statsOrder) {
                Row row = statsSheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(stat);

                for (int i = 0; i < datasets.size(); i++) {
                    Cell cell = row.createCell(i + 1);
                    if (results.containsKey(stat)) {
                        cell.setCellValue(results.get(stat).get(i));
                    }
                }
            }


            Sheet covSheet = workbook.createSheet("Covariance Matrix");


            Row covHeaderRow = covSheet.createRow(0);
            covHeaderRow.createCell(0).setCellValue("Dataset");

            for (int i = 0; i < datasets.size(); i++) {
                covHeaderRow.createCell(i + 1).setCellValue("Dataset " + (i + 1));

                Row dataRow = covSheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue("Dataset " + (i + 1));

                for (int j = 0; j < datasets.size(); j++) {
                    Cell cell = dataRow.createCell(j + 1);
                    Double value = pairwiseResults.get("Covariance").get(i).get(j);
                    cell.setCellValue(value != null ? value : Double.NaN);
                }
            }


            for (int i = 0; i < datasets.size() + 1; i++) {
                statsSheet.autoSizeColumn(i);
                covSheet.autoSizeColumn(i);
            }


            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }

    public List<List<Double>> getDatasets() {
        return datasets;
    }

    public Map<String, List<Double>> getResults() {
        return results;
    }

    public Map<String, Map<Integer, Map<Integer, Double>>> getPairwiseResults() {
        return pairwiseResults;
    }



}
