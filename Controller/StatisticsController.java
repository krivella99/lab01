package Controller;

import Model.DataHandler;
import Model.StatisticsModel;
import View.StatisticsView;

import javax.swing.*;
import java.io.IOException;

public class StatisticsController {
    StatisticsModel model;
    StatisticsView view;
    DataHandler dataHandler;

    public StatisticsController(StatisticsModel model, StatisticsView view, DataHandler dataHandler) {
        this.model = model;
        this.view = view;
        this.dataHandler = dataHandler;

        // Attach action listeners to the view
        this.view.setImportButtonListener(e -> importData());
        this.view.setCalculateButtonListener(e -> calculateData());
        this.view.setExportButtonListener(e -> exportData());
        this.view.setExitButtonListener(e -> System.exit(0));
    }

    private void importData() {
        String filePath = view.showFileDialog(true);
        if (filePath != null) {
            try {
                dataHandler.loadData(filePath);
                view.showMessage("Import successful", "Data has been successfully imported");
                view.updateStatus("Data imported from: " + filePath + ". Click 'Calculate Statistics' to process.");
            } catch (IOException e) {
                view.showErrorMessage("Import Error", "Error importing data: " + e.getMessage());
            }
        }
    }

    private void calculateData() {
        if (dataHandler.getDatasets().isEmpty()) {
            view.showErrorMessage("Calculation Error", "No data to calculate. Please import data first.");
            return;
        }
        try {
            model.calculateStatistics();
            view.showMessage("Calculation successful", "Statistics have been calculated");
            view.updateStatus("Statistics calculated. Ready to export.");
        } catch (Exception e) {
            view.showErrorMessage("Calculation Error", "Error calculating statistics: " + e.getMessage());
        }
    }

    private void exportData() {
        if (dataHandler.getDatasets().isEmpty()) {
            view.showErrorMessage("Export Error", "No data to export. Please import data first.");
            return;
        }
        if (dataHandler.getResults().isEmpty()) {
            view.showErrorMessage("Export Error", "No statistics calculated. Please calculate statistics first.");
            return;
        }

        String filePath = view.showFileDialog(false);
        if (filePath != null) {
            try {
                if (!filePath.toLowerCase().endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }
                dataHandler.exportResults(filePath);
                view.showMessage("Export successful", "Results have been successfully exported");
                view.updateStatus("Results exported to: " + filePath);
            } catch (IOException e) {
                view.showErrorMessage("Export Error", "Error exporting results: " + e.getMessage());
            }
        }
    }
}