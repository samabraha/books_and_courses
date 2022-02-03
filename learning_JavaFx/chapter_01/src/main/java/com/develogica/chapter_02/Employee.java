package com.develogica.chapter_02;

import javafx.beans.property.SimpleObjectProperty;

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

        final Employee employee1 = new Employee();

        employee1.setName("John Adams");
        employee1.setSalary(25000.00);

        final var employee2 = new Employee("George Washington", 55000d);

        computeTax(employee1.getSalary());
        employee1.addPropertyChangeListener(EmployeeTest::handlePropertyChange);

        employee1.setSalary(30_000.0);
        employee1.setSalary(30_000.0);
        employee1.setSalary(40_000.0);

        var counter = new SimpleObjectProperty<Employee>();

        counter.addListener(event -> {

            Employee emp = counter.getValue();
            System.out.printf("-> %s%n", emp);
        });

        counter.setValue(employee2);
        counter.setValue(employee1);
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
