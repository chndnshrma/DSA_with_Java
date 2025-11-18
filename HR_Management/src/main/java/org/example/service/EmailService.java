package org.example.service;

import org.example.model.Employee;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class EmailService {

    private String smtpHost;
    private String smtpPort;
    private String senderEmail;
    private String senderPassword;
    private boolean useSSL;

    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public EmailService(String smtpHost, String smtpPort, String senderEmail, String senderPassword, boolean useSSL) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
        this.useSSL = useSSL;
    }

    public boolean testConnection() {
        try {
            Properties props = createEmailProperties();
            Session session = createSession(props);

            Transport transport = session.getTransport("smtp");
            transport.connect();
            transport.close();
            return true;
        } catch (Exception e) {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }

    public boolean sendEmailToEmployee(Employee employee, String subject, String messageTemplate) {
        try {
            // Clean and validate email address
            String cleanEmail = cleanEmailAddress(employee.getEmail());

            if (!isValidEmail(cleanEmail)) {
                System.err.println("Invalid email address: " + employee.getEmail());
                return false;
            }

            Properties props = createEmailProperties();
            Session session = createSession(props);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cleanEmail));
            message.setSubject(subject);

            // Personalize the message
            String personalizedMessage = personalizeMessage(messageTemplate, employee);
            message.setText(personalizedMessage);

            Transport.send(message);
            System.out.println("✅ Successfully sent email to: " + cleanEmail);
            return true;

        } catch (Exception e) {
            System.err.println("Failed to send email to " + employee.getEmail() + ": " + e.getMessage());
            return false;
        }
    }

    public EmailSendResult sendBulkEmails(List<Employee> employees, String subject, String messageTemplate,
                                          ProgressCallback progressCallback) {
        int total = employees.size();
        int successful = 0;
        int failed = 0;

        // First, validate all email addresses
        List<Employee> validEmployees = new ArrayList<>();
        List<String> invalidEmails = new ArrayList<>();

        for (Employee employee : employees) {
            String cleanEmail = cleanEmailAddress(employee.getEmail());
            if (isValidEmail(cleanEmail)) {
                validEmployees.add(employee);
            } else {
                invalidEmails.add(employee.getEmail());
                System.err.println("❌ Invalid email address: " + employee.getEmail());
            }
        }

        if (!invalidEmails.isEmpty()) {
            System.err.println("Found " + invalidEmails.size() + " invalid email addresses:");
            for (String invalid : invalidEmails) {
                System.err.println("  - " + invalid);
            }
        }

        // Send emails only to valid addresses
        for (int i = 0; i < validEmployees.size(); i++) {
            Employee employee = validEmployees.get(i);

            if (progressCallback != null) {
                double progress = (double) (i + 1) / validEmployees.size();
                progressCallback.onProgress(progress, "Sending to: " + employee.getName());
            }

            boolean success = sendEmailToEmployee(employee, subject, messageTemplate);

            if (success) {
                successful++;
            } else {
                failed++;
            }

            // Add delay to avoid being flagged as spam
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        return new EmailSendResult(total, successful, failed);
    }

    /**
     * Clean email address by removing invisible characters and trimming
     */
    private String cleanEmailAddress(String email) {
        if (email == null) return "";

        // Remove invisible characters (BOM, zero-width spaces, etc.)
        String cleaned = email.replaceAll("[\\uFEFF-\\uFFFF]", "")
                .replaceAll("[\\u200B-\\u200D]", "")
                .replaceAll("[\\uFEFF]", "")
                .trim();

        // Remove any non-printable characters
        cleaned = cleaned.replaceAll("[^\\x20-\\x7E]", "");

        return cleaned;
    }

    /**
     * Validate email format
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private Properties createEmailProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        if (useSSL) {
            props.put("mail.smtp.socketFactory.port", smtpPort);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.ssl.trust", smtpHost);
        } else {
            props.put("mail.smtp.starttls.enable", "true");
        }

        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.ssl.trust", smtpHost);

        // Additional properties for better compatibility
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.smtp.writetimeout", "10000");

        return props;
    }

    private Session createSession(Properties props) {
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
    }

    private String personalizeMessage(String template, Employee employee) {
        return template
                .replace("{{name}}", employee.getName())
                .replace("{{email}}", employee.getEmail())
                .replace("{{department}}", employee.getDepartment() != null ? employee.getDepartment() : "")
                .replace("{{position}}", employee.getPosition() != null ? employee.getPosition() : "")
                .replace("{{firstName}}", getFirstName(employee.getName()));
    }

    private String getFirstName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }
        String[] names = fullName.split(" ");
        return names[0];
    }

    // Simple callback interface for progress updates
    public interface ProgressCallback {
        void onProgress(double progress, String message);
    }

    // Result class
    public static class EmailSendResult {
        private final int total;
        private final int successful;
        private final int failed;

        public EmailSendResult(int total, int successful, int failed) {
            this.total = total;
            this.successful = successful;
            this.failed = failed;
        }

        public int getTotal() { return total; }
        public int getSuccessful() { return successful; }
        public int getFailed() { return failed; }

        public String getSummary() {
            return String.format("Sent: %d, Failed: %d, Total: %d", successful, failed, total);
        }
    }
}