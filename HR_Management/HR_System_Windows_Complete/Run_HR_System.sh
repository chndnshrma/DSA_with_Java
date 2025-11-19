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
