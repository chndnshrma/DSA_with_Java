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
