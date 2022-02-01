package com.develogica.chapter_02;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Employee {
    private String name;
    private Double salary;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Employee() {
        this.name = "Samson Abraha";
        this.salary = 1000d;
    }

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double newSalary) {
        double oldSalary = this.salary;
        this.salary = newSalary;
        pcs.firePropertyChange("salary", oldSalary, newSalary);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        return String.format("name = %s, salary = %,.2f", name, salary);
    }
}

class EmployeeTest {
    public static void main(String[] args) {
        final Employee employee = new Employee("John Adams", 25000.00);

        computeTax(employee.getSalary());
        employee.addPropertyChangeListener(EmployeeTest::handlePropertyChange);

        employee.setSalary(30000.0);
        employee.setSalary(30000.0);
        employee.setSalary(40000.0);
    }

    private static void handlePropertyChange(PropertyChangeEvent event) {
        String propertyName = event.getPropertyName();
        if ("salary".equalsIgnoreCase(propertyName)) {
            System.out.printf("Salary has changed. Old: %,.2f, New: %,.2f%n",
                    (double) event.getOldValue(), (double) event.getNewValue());
            computeTax((double) event.getNewValue());
        }
    }

    private static void computeTax(Double salary) {
        final double TAX_PERCENT = 20.0;
        double tax = salary * (TAX_PERCENT / 100);
        System.out.printf("Salary: %,.2f, Tax: %,.2f%n", salary, tax);
    }


}