package View;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//
//public class StatisticsView {
//    private JFrame frame;
//    private JButton importButton;
//    private JButton calculateButton;
//    private JButton exportButton;
//    private JButton exitButton;
//    private JLabel statusLabel;
//    private JFileChooser fileChooser;
//
//    public StatisticsView() {
//        // Initialize components
//        frame = new JFrame("Statistical Data Analyzer");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//
//        // Make the application fullscreen
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setUndecorated(true);
//
//        // Status panel at the bottom
//        JPanel statusPanel = new JPanel(new BorderLayout());
//        statusLabel = new JLabel("Ready. Please import an XLSX file.");
//        statusPanel.add(statusLabel, BorderLayout.CENTER);
//        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//
//        // Button panel with BoxLayout (horizontal)
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
//
//        importButton = new JButton("Import XLSX");
//        calculateButton = new JButton("Calculate Statistics");
//        exportButton = new JButton("Export Results");
//        exitButton = new JButton("Exit");
//
//        // Add buttons with spacing
//        buttonPanel.add(Box.createHorizontalGlue());
//        buttonPanel.add(importButton);
//        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
//        buttonPanel.add(calculateButton);
//        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
//        buttonPanel.add(exportButton);
//        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
//        buttonPanel.add(exitButton);
//        buttonPanel.add(Box.createHorizontalGlue());
//
//        // Add panels to frame
//        frame.add(createTitlePanel(), BorderLayout.NORTH);
//        frame.add(buttonPanel, BorderLayout.CENTER);
//        frame.add(statusPanel, BorderLayout.SOUTH);
//
//        // Initialize file chooser
//        fileChooser = new JFileChooser();
//
//        // Center on screen (not needed for fullscreen, but kept for compatibility)
//        frame.setLocationRelativeTo(null);
//    }
//
//    private JPanel createTitlePanel() {
//        JPanel titlePanel = new JPanel();
//        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
//        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
//
//        JLabel titleLabel = new JLabel("Statistical Data Analysis");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JLabel subtitleLabel = new JLabel("Import XLSX data and calculate statistical metrics");
//        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
//        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        titlePanel.add(titleLabel);
//        titlePanel.add(Box.createRigidArea(new Dimension(0, 5)));
//        titlePanel.add(subtitleLabel);
//
//        return titlePanel;
//    }
//
//    public void display() {
//        frame.setVisible(true);
//    }
//
//    public String showFileDialog(boolean isOpen) {
//        int result;
//
//        if (isOpen) {
//            fileChooser.setDialogTitle("Select XLSX File to Import");
//            result = fileChooser.showOpenDialog(frame);
//        } else {
//            fileChooser.setDialogTitle("Save Results as XLSX File");
//            result = fileChooser.showSaveDialog(frame);
//        }
//
//        if (result == JFileChooser.APPROVE_OPTION) {
//            return fileChooser.getSelectedFile().getPath();
//        }
//        return null;
//    }
//
//    public void showMessage(String title, String message) {
//        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    public void showErrorMessage(String title, String message) {
//        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
//    }
//
//    public void updateStatus(String status) {
//        statusLabel.setText(status);
//    }
//
//    public void setImportButtonListener(ActionListener listener) {
//        importButton.addActionListener(listener);
//    }
//
//    public void setCalculateButtonListener(ActionListener listener) {
//        calculateButton.addActionListener(listener);
//    }
//
//    public void setExportButtonListener(ActionListener listener) {
//        exportButton.addActionListener(listener);
//    }
//
//    public void setExitButtonListener(ActionListener listener) {
//        exitButton.addActionListener(listener);
//    }
//}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatisticsView {
    private JFrame frame;
    private JButton importButton;
    private JButton calculateButton;
    private JButton exportButton;
    private JButton exitButton;
    private JLabel statusLabel;
    private JFileChooser fileChooser;

    public StatisticsView() {

        frame = new JFrame("Statistical Data Analyzer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        frame.setSize(500, 200);



        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Ready. Please import an XLSX file.");
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        importButton = new JButton("Import XLSX");
        calculateButton = new JButton("Calculate Statistics");
        exportButton = new JButton("Export Results");
        exitButton = new JButton("Exit");


        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(importButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(calculateButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(exportButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createHorizontalGlue());


        frame.add(createTitlePanel(), BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.SOUTH);


        fileChooser = new JFileChooser();


        frame.setLocationRelativeTo(null);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JLabel titleLabel = new JLabel("Statistical Data Analysis");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Import XLSX data and calculate statistical metrics");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        titlePanel.add(subtitleLabel);

        return titlePanel;
    }

    public void display() {
        frame.setVisible(true);
    }

    public String showFileDialog(boolean isOpen) {
        int result;

        if (isOpen) {
            fileChooser.setDialogTitle("Select XLSX File to Import");
            result = fileChooser.showOpenDialog(frame);
        } else {
            fileChooser.setDialogTitle("Save Results as XLSX File");
            result = fileChooser.showSaveDialog(frame);
        }

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getPath();
        }
        return null;
    }

    public void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }

    public void setImportButtonListener(ActionListener listener) {
        importButton.addActionListener(listener);
    }

    public void setCalculateButtonListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }

    public void setExportButtonListener(ActionListener listener) {
        exportButton.addActionListener(listener);
    }

    public void setExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}