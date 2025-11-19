#!/bin/bash

echo "🖥️  Building Windows EXE for HR System..."
echo "=========================================="

# Check if we're on Mac
if [[ "$OSTYPE" != "darwin"* ]]; then
    echo "❌ This script is for Mac only"
    exit 1
fi

# Check requirements
echo "🔍 Checking requirements..."

if ! command -v java &> /dev/null; then
    echo "❌ Java not found. Install Java 17+"
    exit 1
fi

if ! command -v mvn &> /dev/null; then
    echo "❌ Maven not found. Install Maven"
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | head -n1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt "17" ]; then
    echo "❌ Java 17+ required. Found: $JAVA_VERSION"
    exit 1
fi

echo "✅ Java version: $(java -version 2>&1 | head -n1)"
echo "✅ Maven version: $(mvn --version | head -n1)"

# Create directories
mkdir -p windows-build
mkdir -p dist

# Step 1: Build the JAR with all dependencies
echo "📦 Step 1: Building JAR file with all dependencies..."
mvn clean compile

# Create a proper fat JAR using Maven Shade Plugin
cat > pom-packaging.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>HR_System</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <javafx.version>17.0.9</javafx.version>
        <main.class>org.example.Main</main.class>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-base</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-graphics</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>com.sun.mail</groupId>
            <artifactId>javax.mail</artifactId>
            <version>1.6.2</version>
        </dependency>
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1.1</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>

            <!-- Create fat JAR with dependencies -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.4.1</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>${main.class}</mainClass>
                                </transformer>
                            </transformers>
                            <filters>
                                <filter>
                                    <artifact>*:*</artifact>
                                    <excludes>
                                        <exclude>module-info.class</exclude>
                                        <exclude>META-INF/*.SF</exclude>
                                        <exclude>META-INF/*.DSA</exclude>
                                        <exclude>META-INF/*.RSA</exclude>
                                    </excludes>
                                </filter>
                            </filters>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
EOF

# Use the packaging POM
mvn -f pom-packaging.xml clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Maven build failed"
    rm pom-packaging.xml
    exit 1
fi

echo "✅ JAR built successfully: target/HR_System-1.0.0.jar"

# Step 2: Create Windows EXE using jpackage
echo "🔨 Step 2: Creating Windows EXE using jpackage..."

# Check if jpackage exists
if ! command -v jpackage &> /dev/null; then
    echo "❌ jpackage not found. It's included in JDK 14+"
    echo "💡 Make sure you're using JDK 14 or higher"
    exit 1
fi

# Create the EXE
jpackage \
  --type exe \
  --input target/ \
  --main-jar HR_System-1.0.0.jar \
  --main-class org.example.Main \
  --name "HR System" \
  --app-version "1.0.0" \
  --vendor "ChndnSharma" \
  --copyright "Copyright 2025" \
  --description "HR Employee Email Management System" \
  --dest dist/ \
  --java-options "-Xmx512m" \
  --java-options "-Dfile.encoding=UTF-8" \
  --verbose

if [ $? -eq 0 ]; then
    echo "🎉 SUCCESS! Windows EXE created!"
    echo "📁 Location: dist/HR System.exe"

    # Create a simple distribution package
    mkdir -p "HR_System_Windows"
    cp "dist/HR System.exe" "HR_System_Windows/HR_System.exe"

    # Create README for Windows users
    cat > "HR_System_Windows/README.txt" << 'EOF'
HR System - Employee Email Manager
==================================

System Requirements:
- Windows 10 or later
- No Java installation required! (Java is bundled with the application)

How to Use:
1. Double-click "HR_System.exe" to start the application
2. Configure your email settings (Gmail, Outlook, etc.)
3. Load employee data from CSV or enter manually
4. Compose your email and attach files if needed
5. Send emails to all employees

Features:
- Send bulk emails to employees
- File attachments support
- CSV import/export
- Email personalization with placeholders
- SMTP configuration for major email providers

Troubleshooting:
- If the app doesn't start, try running as Administrator
- Make sure your firewall allows the application
- For Gmail: Use "App Passwords" if 2FA is enabled

Support:
Version: 1.0.0
Contact: your-support@company.com
EOF

    # Create sample CSV
    cat > "HR_System_Windows/sample_employees.csv" << 'EOF'
email,name,department,position
john.doe@company.com,John Doe,IT,Software Developer
jane.smith@company.com,Jane Smith,HR,Manager
mike.wilson@company.com,Mike Wilson,Finance,Analyst
sarah.johnson@company.com,Sarah Johnson,Marketing,Specialist
robert.brown@company.com,Robert Brown,Operations,Coordinator
emily.davis@company.com,Emily Davis,Sales,Representative
EOF

    echo ""
    echo "📦 Distribution package created: HR_System_Windows/"
    echo "   ├── HR_System.exe (Main application)"
    echo "   ├── README.txt (Instructions)"
    echo "   └── sample_employees.csv (Sample data)"
    echo ""
    echo "🚀 You can now zip the 'HR_System_Windows' folder and distribute it!"

else
    echo "❌ jpackage failed"
    echo "💡 This might be because jpackage on Mac cannot create Windows EXE directly"
    echo "💡 Try Method 2 below for cross-platform JAR distribution"
fi

# Clean up
rm pom-packaging.xml

echo ""
echo "💡 If jpackage didn't work, try the cross-platform JAR method below:"