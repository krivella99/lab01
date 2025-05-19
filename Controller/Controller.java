package Controller;
import View.MainView;
import Model.Calculator;
import Model.DataHandler;
import Model.DataExporter;
import View.MainView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Controller {
    private MainView view;
    private DataHandler dataHandler;
    private Calculator calculator;
    private DataExporter dataExporter;
    private boolean dataImported = false;
    private boolean calculationDone = false;

    public Controller() {
        this.dataHandler = new DataHandler();
        this.calculator = new Calculator();
        this.dataExporter = new DataExporter();

            this.view = new MainView(this);
    }

    public void startApp() {
        // Показываем главное окно
        SwingUtilities.invokeLater(() -> {
            view.setVisible(true);
        });
    }

    public void importData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файл Excel для импорта");

        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                dataHandler.importData(selectedFile.getAbsolutePath());
                view.updateStatus("Данные успешно импортированы.");
                view.enableCalculateButton(true);
                dataImported = true;
                calculationDone = false;
                view.enableExportButton(false);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        view,
                        "Ошибка при импорте данных: " + e.getMessage(),
                        "Ошибка импорта",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public void calculateData() {
        if (!dataImported) {
            JOptionPane.showMessageDialog(
                    view,
                    "Сначала необходимо импортировать данные.",
                    "Ошибка расчета",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            calculator.calculateStats(dataHandler);
            view.updateStatus("Статистика успешно рассчитана.");
            view.enableExportButton(true);
            calculationDone = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    view,
                    "Ошибка при расчете статистики: " + e.getMessage(),
                    "Ошибка расчета",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void exportData() {
        if (!calculationDone) {
            JOptionPane.showMessageDialog(
                    view,
                    "Сначала необходимо рассчитать статистику.",
                    "Ошибка экспорта",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файл для экспорта результатов");

        int result = fileChooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Проверяем расширение файла
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            try {
                dataExporter.exportData(filePath, calculator);
                view.updateStatus("Данные успешно экспортированы в файл: " + filePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        view,
                        "Ошибка при экспорте данных: " + e.getMessage(),
                        "Ошибка экспорта",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public void exit() {
        System.exit(0);
    }
}
