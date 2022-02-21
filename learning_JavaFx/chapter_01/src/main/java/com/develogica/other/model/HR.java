package com.develogica.other.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class HR {
    List<Employee> employees = new ArrayList<>();


    public void hire(Employee employee) {
        employees.add(employee);
    }

    public boolean layOff(Employee employee) {
        return employees.remove(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void printEmployees() {
        employees.forEach(System.out::println);
    }

    public void payEverybody() {
        for (Employee employee : employees) {
            System.out.printf("Paying %s %s%n",
                    employee.getName(),
                    NumberFormat.getCurrencyInstance().format(employee.getPay()));
        }
    }
}

