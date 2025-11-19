HR System - Employee Email Manager
==================================

📋 SYSTEM REQUIREMENTS:
- Windows 10/11 or macOS 10.14+ or Linux
- Java 17 or higher (see installation instructions below)

🚀 QUICK START:

OPTION 1: Double-click Method (Easiest)
----------------------------------------
1. Double-click "Run_HR_System.bat"
2. The application will start automatically

OPTION 2: Manual Method
-----------------------
1. Open Command Prompt/Terminal
2. Navigate to this folder
3. Run: java -jar HR_System-1.0.0.jar

📥 JAVA INSTALLATION (if not installed):
----------------------------------------
1. Download Java from: https://adoptium.net/temurin/releases/
2. Choose "Temurin 17" for your operating system
3. Run the installer
4. Restart your computer
5. Try running the application again

🔧 TROUBLESHOOTING:
-------------------
❌ "Java not found" error:
   - Install Java from the link above
   - Make sure to restart after installation

❌ Application won't start:
   - Try running as Administrator
   - Check if your antivirus is blocking the application

❌ Email sending fails:
   - Check your SMTP settings
   - For Gmail: Use "App Passwords" if 2FA is enabled
   - Make sure your firewall allows the application

✨ FEATURES:
-----------
✅ Send bulk emails to employees
✅ File attachments support (PDF, Word, Excel, Images)
✅ CSV import/export for employee data
✅ Email personalization with placeholders
✅ SMTP configuration for Gmail, Outlook, Yahoo, Office 365
✅ Real-time email validation
✅ Progress tracking for email sending

📊 SUPPORTED PLACEHOLDERS:
--------------------------
{{name}} - Full employee name
{{firstName}} - First name only
{{email}} - Email address
{{department}} - Department
{{position}} - Job position

📝 SAMPLE CSV FORMAT:
---------------------
email,name,department,position
john@company.com,John Doe,IT,Developer
jane@company.com,Jane Smith,HR,Manager

📧 EMAIL PROVIDER SETTINGS:
---------------------------
Gmail: smtp.gmail.com, Port 587, Use App Password if 2FA enabled
Outlook: smtp-mail.outlook.com, Port 587
Yahoo: smtp.mail.yahoo.com, Port 587
Office 365: smtp.office365.com, Port 587

📞 SUPPORT:
-----------
Version: 1.0.0
Contact: chndnsharma5036@gmail..com

For quick setup, use the "sample_employees.csv" file included!
