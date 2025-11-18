package org.example.controller;

import org.example.model.Employee;
import org.example.service.EmailService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.concurrent.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class EmailController {

    // SMTP Configuration Fields
    @FXML private TextField smtpHostField;
    @FXML private TextField smtpPortField;
    @FXML private TextField senderEmailField;
    @FXML private PasswordField senderPasswordField;
    @FXML private CheckBox sslCheckBox;

    // Email Content Fields
    @FXML private TextField subjectField;
    @FXML private TextArea messageArea;

    // Employee Management Fields
    @FXML private TextArea employeesTextArea;
    @FXML private TableView<Employee> employeesTableView;
    @FXML private TextField searchField;

    // Status and Progress Fields
    @FXML private Label statusLabel;
    @FXML private ProgressBar progressBar;
    @FXML private Label progressLabel;
    @FXML private Button sendButton;
    @FXML private Button testConnectionButton;
    @FXML private Label employeeCountLabel;

    // Quick Setup Buttons
    @FXML private Button gmailSetupButton;
    @FXML private Button outlookSetupButton;
    @FXML private Button yahooSetupButton;
    @FXML private Button office365SetupButton;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private EmailService emailService;

    @FXML
    private void initialize() {
        setupDefaultValues();
        setupEventHandlers();

        employeesTableView.setItems(employees);
        updateEmployeeCount();
    }

    private void setupDefaultValues() {
        // Set default SMTP values for Gmail
        smtpHostField.setText("smtp.gmail.com");
        smtpPortField.setText("587");
        sslCheckBox.setSelected(false);

        // Set placeholder text
        messageArea.setPromptText("Dear {{name}},\n\nWe are writing to inform you...\n\nBest regards,\nHR Team");
        employeesTextArea.setPromptText("Enter employee details (one per line):\nemail,name,department,position\n\nExample:\njohn@company.com,John Doe,IT,Developer\njane@company.com,Jane Smith,HR,Manager");

        // Hide progress initially
        progressBar.setVisible(false);
        progressLabel.setVisible(false);
    }

    private void setupEventHandlers() {
        // Real-time employee list parsing
        employeesTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            parseEmployeesFromText();
            updateEmployeeCount();
        });

        // Search functionality
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterEmployees(newValue);
        });
    }

    // Quick Setup Methods
    @FXML
    private void setupGmail() {
        smtpHostField.setText("smtp.gmail.com");
        smtpPortField.setText("587");
        sslCheckBox.setSelected(false);
        showInfo("Gmail configuration loaded. Use App Password if 2FA is enabled.");
    }

    @FXML
    private void setupOutlook() {
        smtpHostField.setText("smtp-mail.outlook.com");
        smtpPortField.setText("587");
        sslCheckBox.setSelected(false);
        showInfo("Outlook configuration loaded.");
    }

    @FXML
    private void setupYahoo() {
        smtpHostField.setText("smtp.mail.yahoo.com");
        smtpPortField.setText("587");
        sslCheckBox.setSelected(false);
        showInfo("Yahoo configuration loaded. Use App Password if 2FA is enabled.");
    }

    @FXML
    private void setupOffice365() {
        smtpHostField.setText("smtp.office365.com");
        smtpPortField.setText("587");
        sslCheckBox.setSelected(false);
        showInfo("Office 365 configuration loaded.");
    }

    private void updateEmployeeCount() {
        employeeCountLabel.setText("📊 Employee Count: " + employees.size());
    }

    @FXML
    private void testConnection() {
        if (!validateSmtpConfig()) return;

        String host = smtpHostField.getText();
        String port = smtpPortField.getText();
        String email = senderEmailField.getText();
        String password = senderPasswordField.getText();
        boolean useSSL = sslCheckBox.isSelected();  // Get SSL setting

        emailService = new EmailService(host, port, email, password, useSSL);  // Now 5 arguments

        Task<Boolean> testTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return emailService.testConnection();
            }
        };

        testTask.setOnSucceeded(e -> {
            if (testTask.getValue()) {
                showSuccess("✅ Connection test successful!");
            } else {
                showError("❌ Connection test failed. Check your credentials and settings.");
            }
        });

        testTask.setOnFailed(e -> {
            showError("❌ Connection test failed: " + testTask.getException().getMessage());
        });

        new Thread(testTask).start();
    }

    @FXML
    private void sendEmails() {
        if (!validateAllInputs()) return;

        if (employees.isEmpty()) {
            showError("❌ No employees found. Please add employee data.");
            return;
        }

        // Confirmation dialog
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Email Sending");
        confirmation.setHeaderText("Send emails to " + employees.size() + " employees?");
        confirmation.setContentText("This will send the email to all employees in the list.");

        if (confirmation.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }

        // Initialize email service with all 5 parameters
        String host = smtpHostField.getText();
        String port = smtpPortField.getText();
        String email = senderEmailField.getText();
        String password = senderPasswordField.getText();
        boolean useSSL = sslCheckBox.isSelected();  // Get SSL setting

        emailService = new EmailService(host, port, email, password, useSSL);  // Now 5 arguments

        Task<EmailService.EmailSendResult> sendTask = new Task<EmailService.EmailSendResult>() {
            @Override
            protected EmailService.EmailSendResult call() throws Exception {
                return emailService.sendBulkEmails(
                        new ArrayList<>(employees),
                        subjectField.getText(),
                        messageArea.getText(),
                        new EmailService.ProgressCallback() {
                            @Override
                            public void onProgress(double progress, String message) {
                                updateProgress(progress, 1.0);
                                updateMessage(message);
                            }
                        }
                );
            }
        };

        // Bind UI to task progress
        progressBar.progressProperty().bind(sendTask.progressProperty());
        progressLabel.textProperty().bind(sendTask.messageProperty());

        progressBar.setVisible(true);
        progressLabel.setVisible(true);
        sendButton.setDisable(true);
        testConnectionButton.setDisable(true);

        sendTask.setOnSucceeded(e -> {
            EmailService.EmailSendResult result = sendTask.getValue();
            progressBar.setVisible(false);
            progressLabel.setVisible(false);
            sendButton.setDisable(false);
            testConnectionButton.setDisable(false);

            showSuccess("✅ Email sending completed!\n" + result.getSummary());
        });

        sendTask.setOnFailed(e -> {
            progressBar.setVisible(false);
            progressLabel.setVisible(false);
            sendButton.setDisable(false);
            testConnectionButton.setDisable(false);

            showError("❌ Failed to send emails: " + sendTask.getException().getMessage());
        });

        new Thread(sendTask).start();
    }

    // Email validation method
    @FXML
    private void validateEmails() {
        parseEmployeesFromText();

        List<String> invalidEmails = new ArrayList<>();
        List<String> validEmails = new ArrayList<>();

        for (Employee employee : employees) {
            String email = employee.getEmail();
            // Check for invisible characters
            if (email.contains("\uFEFF") || email.contains("\u200B") || email.contains("\u200C") || email.contains("\u200D")) {
                invalidEmails.add(email + " (contains invisible characters)");
            } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                invalidEmails.add(email + " (invalid format)");
            } else {
                validEmails.add(email);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Email Validation Results:\n\n");
        result.append("✅ Valid Emails: ").append(validEmails.size()).append("\n");
        result.append("❌ Invalid Emails: ").append(invalidEmails.size()).append("\n\n");

        if (!invalidEmails.isEmpty()) {
            result.append("Invalid Email Addresses:\n");
            for (String invalid : invalidEmails) {
                result.append("• ").append(invalid).append("\n");
            }
        }

        // Show results in dialog
        TextArea textArea = new TextArea(result.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(600, 400);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Email Validation Results");
        alert.setHeaderText("Email Address Validation");
        alert.getDialogPane().setContent(scrollPane);
        alert.showAndWait();
    }

    // Test single email method
    @FXML
    private void testSingleEmail() {
        if (!validateSmtpConfig()) return;

        // Test with a simple known-good email
        Employee testEmployee = new Employee("test@example.com", "Test User");

        String host = smtpHostField.getText();
        String port = smtpPortField.getText();
        String email = senderEmailField.getText();
        String password = senderPasswordField.getText();
        boolean useSSL = sslCheckBox.isSelected();  // Get SSL setting

        EmailService testService = new EmailService(host, port, email, password, useSSL);  // Now 5 arguments

        Task<Boolean> testTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return testService.sendEmailToEmployee(testEmployee, "Test Email", "This is a test email from HR System.");
            }
        };

        testTask.setOnSucceeded(e -> {
            if (testTask.getValue()) {
                showSuccess("✅ Test email sent successfully!");
            } else {
                showError("❌ Failed to send test email. Check your SMTP configuration.");
            }
        });

        new Thread(testTask).start();
    }

    @FXML
    private void loadFromCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Employee CSV File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                employees.clear();

                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;

                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        String email = parts[0].trim();
                        String name = parts[1].trim();
                        String department = parts.length > 2 ? parts[2].trim() : "";
                        String position = parts.length > 3 ? parts[3].trim() : "";

                        employees.add(new Employee(email, name, department, position));
                        sb.append(email).append(",").append(name);
                        if (!department.isEmpty()) sb.append(",").append(department);
                        if (!position.isEmpty()) sb.append(",").append(position);
                        sb.append("\n");
                    }
                }

                employeesTextArea.setText(sb.toString());
                updateEmployeeCount();
                showSuccess("✅ Loaded " + employees.size() + " employees from CSV");

            } catch (Exception e) {
                showError("❌ Error loading CSV: " + e.getMessage());
            }
        }
    }

    @FXML
    private void saveToCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Employee CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                for (Employee employee : employees) {
                    writer.write(employee.toCSV() + "\n");
                }
                showSuccess("✅ Saved " + employees.size() + " employees to CSV");
            } catch (Exception e) {
                showError("❌ Error saving CSV: " + e.getMessage());
            }
        }
    }

    @FXML
    private void clearEmployees() {
        employees.clear();
        employeesTextArea.clear();
        updateEmployeeCount();
        showInfo("Employee list cleared");
    }

    @FXML
    private void addSampleData() {
        String sampleData = "john.doe@company.com,John Doe,IT,Software Developer\n" +
                "jane.smith@company.com,Jane Smith,HR,Manager\n" +
                "mike.wilson@company.com,Mike Wilson,Finance,Analyst\n" +
                "sarah.johnson@company.com,Sarah Johnson,Marketing,Specialist";

        employeesTextArea.setText(sampleData);
        showInfo("Sample data added");
    }

    private void parseEmployeesFromText() {
        employees.clear();
        String[] lines = employeesTextArea.getText().split("\n");

        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty() && !line.startsWith("#")) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String email = parts[0].trim();
                    String name = parts[1].trim();
                    String department = parts.length > 2 ? parts[2].trim() : "";
                    String position = parts.length > 3 ? parts[3].trim() : "";

                    if (!email.isEmpty() && !name.isEmpty()) {
                        employees.add(new Employee(email, name, department, position));
                    }
                }
            }
        }
    }

    private void filterEmployees(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            employeesTableView.setItems(employees);
            return;
        }

        String lowerCaseFilter = searchText.toLowerCase();
        ObservableList<Employee> filteredList = FXCollections.observableArrayList();

        for (Employee employee : employees) {
            if (employee.getName().toLowerCase().contains(lowerCaseFilter) ||
                    employee.getEmail().toLowerCase().contains(lowerCaseFilter) ||
                    (employee.getDepartment() != null && employee.getDepartment().toLowerCase().contains(lowerCaseFilter)) ||
                    (employee.getPosition() != null && employee.getPosition().toLowerCase().contains(lowerCaseFilter))) {
                filteredList.add(employee);
            }
        }

        employeesTableView.setItems(filteredList);
    }

    private boolean validateSmtpConfig() {
        if (smtpHostField.getText().isEmpty() || smtpPortField.getText().isEmpty() ||
                senderEmailField.getText().isEmpty() || senderPasswordField.getText().isEmpty()) {
            showError("❌ Please fill all SMTP configuration fields");
            return false;
        }
        return true;
    }

    private boolean validateAllInputs() {
        if (!validateSmtpConfig()) return false;

        if (subjectField.getText().isEmpty()) {
            showError("❌ Please enter email subject");
            return false;
        }

        if (messageArea.getText().isEmpty()) {
            showError("❌ Please enter email message");
            return false;
        }

        if (employees.isEmpty()) {
            showError("❌ No employees found. Please add employee data.");
            return false;
        }

        return true;
    }

    private void showSuccess(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
    }

    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
    }

    private void showInfo(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #3498db; -fx-font-weight: bold;");
    }
}