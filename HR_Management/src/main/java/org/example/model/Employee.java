package org.example.model;

public class Employee {
    private String email;
    private String name;
    private String department;
    private String position;

    public Employee(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Employee(String email, String name, String department, String position) {
        this.email = email;
        this.name = name;
        this.department = department;
        this.position = position;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    @Override
    public String toString() {
        return String.format("%s <%s> - %s, %s", name, email, department, position);
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%s", email, name, department, position);
    }
}