package org.example.controller;

import org.example.model.Employee;
import org.example.service.EmailService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.concurrent.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // Attachment Fields
    @FXML private ListView<File> attachmentsListView;
    @FXML private Label attachmentsCountLabel;

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

    // Statistics Labels
    @FXML private Label employeeCountLabel;
    @FXML private Label departmentStatsLabel;
    @FXML private Label validEmailsLabel;
    @FXML private Label invalidEmailsLabel;

    // Quick Setup Buttons
    @FXML private Button gmailSetupButton;
    @FXML private Button outlookSetupButton;
    @FXML private Button yahooSetupButton;
    @FXML private Button office365SetupButton;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private ObservableList<File> attachments = FXCollections.observableArrayList();
    private EmailService emailService;

    @FXML
    private void initialize() {
        setupDefaultValues();
        setupEventHandlers();

        employeesTableView.setItems(employees);
        attachmentsListView.setItems(attachments);

        // Custom cell factory for attachments list
        attachmentsListView.setCellFactory(lv -> new ListCell<File>() {
            @Override
            protected void updateItem(File file, boolean empty) {
                super.updateItem(file, empty);
                if (empty || file == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(10);
                    hbox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

                    Label nameLabel = new Label(file.getName());
                    Label sizeLabel = new Label(formatFileSize(file.length()));
                    sizeLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 11px;");

                    Button removeButton = new Button("✕");
                    removeButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 10px;");
                    removeButton.setOnAction(e -> removeAttachment(file));

                    hbox.getChildren().addAll(nameLabel, sizeLabel, removeButton);
                    setGraphic(hbox);
                    setText(null);
                }
            }
        });

        updateEmployeeStatistics();
        updateAttachmentsCount();
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
            updateEmployeeStatistics();
        });

        // Search functionality
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterEmployees(newValue);
        });
    }

    // Enhanced Statistics Methods
    private void updateEmployeeStatistics() {
        int totalEmployees = employees.size();

        // Count valid and invalid emails
        int validEmails = 0;
        int invalidEmails = 0;
        Set<String> departments = new HashSet<>();

        for (Employee employee : employees) {
            String cleanEmail = cleanEmailAddress(employee.getEmail());
            if (isValidEmail(cleanEmail)) {
                validEmails++;
            } else {
                invalidEmails++;
            }

            if (employee.getDepartment() != null && !employee.getDepartment().trim().isEmpty()) {
                departments.add(employee.getDepartment());
            }
        }

        // Update all statistics labels
        employeeCountLabel.setText("📊 Total Employees: " + totalEmployees);
        departmentStatsLabel.setText("🏢 Departments: " + departments.size());
        validEmailsLabel.setText("✅ Valid Emails: " + validEmails);
        invalidEmailsLabel.setText("❌ Invalid Emails: " + invalidEmails);
    }

    private String cleanEmailAddress(String email) {
        if (email == null) return "";
        return email.replaceAll("[\\uFEFF-\\uFFFF]", "")
                .replaceAll("[\\u200B-\\u200D]", "")
                .replaceAll("[\\uFEFF]", "")
                .trim()
                .replaceAll("[^\\x20-\\x7E]", "");
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    // Attachment Methods
    @FXML
    private void addAttachments() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Files to Attach");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Word Documents", "*.doc", "*.docx"),
                new FileChooser.ExtensionFilter("Excel Files", "*.xls", "*.xlsx"),
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                if (!attachments.contains(file)) {
                    attachments.add(file);
                    System.out.println("Added attachment: " + file.getName());
                }
            }
            updateAttachmentsCount();
        }
    }

    @FXML
    private void clearAttachments() {
        attachments.clear();
        updateAttachmentsCount();
        showInfo("All attachments cleared");
    }

    private void removeAttachment(File file) {
        attachments.remove(file);
        updateAttachmentsCount();
        showInfo("Removed attachment: " + file.getName());
    }

    private void updateAttachmentsCount() {
        long totalSize = attachments.stream().mapToLong(File::length).sum();
        attachmentsCountLabel.setText("📎 Attachments: " + attachments.size() + " files (" + formatFileSize(totalSize) + ")");
    }

    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.1f MB", bytes / (1024.0 * 1024.0));
        return String.format("%.1f GB", bytes / (1024.0 * 1024.0 * 1024.0));
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

    @FXML
    private void testConnection() {
        if (!validateSmtpConfig()) return;

        String host = smtpHostField.getText();
        String port = smtpPortField.getText();
        String email = senderEmailField.getText();
        String password = senderPasswordField.getText();
        boolean useSSL = sslCheckBox.isSelected();

        emailService = new EmailService(host, port, email, password, useSSL);

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

        // Check attachment size (limit to 25MB for most email providers)
        long totalAttachmentSize = attachments.stream().mapToLong(File::length).sum();
        if (totalAttachmentSize > 25 * 1024 * 1024) {
            showError("❌ Total attachment size exceeds 25MB. Please reduce file sizes.");
            return;
        }

        // Confirmation dialog with attachment info
        StringBuilder confirmationText = new StringBuilder();
        confirmationText.append("This will send the email to all ").append(employees.size()).append(" employees.");

        if (!attachments.isEmpty()) {
            confirmationText.append("\n\nAttachments (").append(attachments.size()).append(" files):\n");
            for (File file : attachments) {
                confirmationText.append("• ").append(file.getName()).append(" (").append(formatFileSize(file.length())).append(")\n");
            }
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Email Sending");
        confirmation.setHeaderText("Send emails to " + employees.size() + " employees?");
        confirmation.setContentText(confirmationText.toString());

        if (confirmation.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }

        // Initialize email service
        String host = smtpHostField.getText();
        String port = smtpPortField.getText();
        String email = senderEmailField.getText();
        String password = senderPasswordField.getText();
        boolean useSSL = sslCheckBox.isSelected();

        emailService = new EmailService(host, port, email, password, useSSL);

        Task<EmailService.EmailSendResult> sendTask = new Task<EmailService.EmailSendResult>() {
            @Override
            protected EmailService.EmailSendResult call() throws Exception {
                return emailService.sendBulkEmails(
                        new ArrayList<>(employees),
                        subjectField.getText(),
                        messageArea.getText(),
                        new ArrayList<>(attachments), // Pass attachments
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

            String successMessage = "✅ Email sending completed!\n" + result.getSummary();
            if (!attachments.isEmpty()) {
                successMessage += "\n📎 " + attachments.size() + " files attached to each email";
            }
            showSuccess(successMessage);
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
                updateEmployeeStatistics();
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
        updateEmployeeStatistics();
        showInfo("Employee list cleared");
    }

    @FXML
    private void addSampleData() {
        String sampleData = "john.doe@company.com,John Doe,IT,Software Developer\n" +
                "jane.smith@company.com,Jane Smith,HR,Manager\n" +
                "mike.wilson@company.com,Mike Wilson,Finance,Analyst\n" +
                "sarah.johnson@company.com,Sarah Johnson,Marketing,Specialist\n" +
                "robert.brown@company.com,Robert Brown,Operations,Coordinator\n" +
                "emily.davis@company.com,Emily Davis,Sales,Representative\n" +
                "david.miller@company.com,David Miller,IT,System Administrator\n" +
                "lisa.anderson@company.com,Lisa Anderson,HR,Recruiter";

        employeesTextArea.setText(sampleData);
        showInfo("Sample data added");
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
            } else if (!isValidEmail(email)) {
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
        boolean useSSL = sslCheckBox.isSelected();

        EmailService testService = new EmailService(host, port, email, password, useSSL);

        Task<Boolean> testTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return testService.sendEmailToEmployee(testEmployee, "Test Email", "This is a test email from HR System.", attachments);
            }
        };

        testTask.setOnSucceeded(e -> {
            if (testTask.getValue()) {
                String successMsg = "✅ Test email sent successfully!";
                if (!attachments.isEmpty()) {
                    successMsg += " (" + attachments.size() + " attachments included)";
                }
                showSuccess(successMsg);
            } else {
                showError("❌ Failed to send test email. Check your SMTP configuration.");
            }
        });

        new Thread(testTask).start();
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
        updateEmployeeStatistics();
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