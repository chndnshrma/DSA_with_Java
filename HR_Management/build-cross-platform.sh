#!/bin/bash

echo "📦 Building Cross-Platform Distribution Package..."
echo "=================================================="

# Clean and build
mvn clean compile
mvn package -DskipTests
# Create distribution folder
DIST_DIR="HR_System_Windows_Complete"
mkdir -p "$DIST_DIR"

# Copy the JAR
cp target/HR_System-1.0.0.jar "$DIST_DIR/"

# Create Windows batch file with enhanced features
cat > "$DIST_DIR/Run_HR_System.bat" << 'EOF'
@echo off
chcp 65001 > nul
title HR System - Employee Email Manager
echo.
echo ========================================
echo    HR System - Employee Email Manager
echo ========================================
echo.
echo Checking Java installation...

:: Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo.
    echo ❌ ERROR: Java is not installed or not in PATH
    echo.
    echo 📥 Please install Java 17 or higher from:
    echo    https://adoptium.net/temurin/releases/
    echo.
    echo 🔧 Download "Temurin 17" (MSI Installer)
    echo 📝 After installation, restart your computer and try again
    echo.
    pause
    exit /b 1
)

:: Check Java version
for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    set java_version=%%g
)

set java_version=%java_version:"=%
for /f "tokens=1,2 delims=." %%a in ("%java_version%") do (
    set major=%%a
    set minor=%%b
)

if %major% lss 17 (
    echo.
    echo ❌ ERROR: Java 17 or higher required
    echo.
    echo 📥 Please install Java 17 or higher from:
    echo    https://adoptium.net/temurin/releases/
    echo.
    echo 🔧 Download "Temurin 17" (MSI Installer)
    echo.
    pause
    exit /b 1
)

echo ✅ Java %java_version% detected
echo.
echo 🚀 Starting HR System...
echo ⏳ Please wait...
echo.

:: Run the application with optimized settings
java -Xmx512m -Dfile.encoding=UTF-8 -jar "HR_System-1.0.0.jar"

if %errorlevel% neq 0 (
    echo.
    echo ❌ ERROR: Failed to start application
    echo.
    echo 🔧 Troubleshooting steps:
    echo    1. Make sure Java 17+ is installed
    echo    2. Try running as Administrator
    echo    3. Check if the JAR file is not corrupted
    echo.
    pause
)
EOF

# Create Mac/Linux launcher
cat > "$DIST_DIR/Run_HR_System.sh" << 'EOF'
#!/bin/bash

echo "HR System - Employee Email Manager"
echo "=================================="

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ ERROR: Java is not installed or not in PATH"
    echo ""
    echo "📥 Please install Java 17 or higher from:"
    echo "   https://adoptium.net/temurin/releases/"
    echo ""
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | head -n1 | cut -d'"' -f2 | cut -d'.' -f1)

if [ "$JAVA_VERSION" -lt "17" ]; then
    echo "❌ ERROR: Java 17 or higher required"
    echo "📥 Please install Java 17 or higher from:"
    echo "   https://adoptium.net/temurin/releases/"
    exit 1
fi

echo "✅ Java version: $(java -version 2>&1 | head -n1)"
echo "🚀 Starting HR System..."

# Run the application
java -Xmx512m -Dfile.encoding=UTF-8 -jar "HR_System-1.0.0.jar"
EOF

chmod +x "$DIST_DIR/Run_HR_System.sh"

# Create comprehensive README
cat > "$DIST_DIR/README_Windows.txt" << 'EOF'
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
Contact: your-support@company.com

For quick setup, use the "sample_employees.csv" file included!
EOF

# Create sample CSV
cat > "$DIST_DIR/sample_employees.csv" << 'EOF'
# Employee Email List
# Format: email,name,department,position
john.doe@company.com,John Doe,IT,Software Developer
jane.smith@company.com,Jane Smith,HR,Manager
mike.wilson@company.com,Mike Wilson,Finance,Analyst
sarah.johnson@company.com,Sarah Johnson,Marketing,Specialist
robert.brown@company.com,Robert Brown,Operations,Coordinator
emily.davis@company.com,Emily Davis,Sales,Representative
david.miller@company.com,David Miller,IT,System Administrator
lisa.anderson@company.com,Lisa Anderson,HR,Recruiter
EOF

# Create a quick setup guide
cat > "$DIST_DIR/Quick_Setup_Guide.txt" << 'EOF'
HR System - Quick Setup Guide
=============================

1. FIRST TIME SETUP:
   - Double-click "Run_HR_System.bat"
   - If Java error appears, install Java from:
     https://adoptium.net/temurin/releases/

2. CONFIGURE EMAIL:
   - Click "Gmail" button for automatic setup
   - Enter your email and App Password (if 2FA enabled)
   - Click "Test Connection" to verify

3. ADD EMPLOYEES:
   - Click "Load CSV" and select "sample_employees.csv"
   - Or manually enter employees in the text area

4. SEND EMAILS:
   - Enter email subject and message
   - Use {{name}} for personalization
   - Click "Send Emails to All Employees"

Need help? Check README_Windows.txt for detailed instructions!
EOF

echo "✅ Distribution package created: $DIST_DIR/"
echo ""
echo "📁 Contents:"
echo "   ├── Run_HR_System.bat (Windows - Double click this!)"
echo "   ├── Run_HR_System.sh (Mac/Linux)"
echo "   ├── HR_System-1.0.0.jar (Main application)"
echo "   ├── README_Windows.txt (Complete instructions)"
echo "   ├── Quick_Setup_Guide.txt (Quick start)"
echo "   └── sample_employees.csv (Sample data)"
echo ""
echo "🚀 To distribute:"
echo "   1. Zip the '$DIST_DIR' folder"
echo "   2. Send the ZIP file to Windows users"
echo "   3. Users extract and double-click 'Run_HR_System.bat'"
echo ""
echo "🎉 Your HR System is ready for distribution!"