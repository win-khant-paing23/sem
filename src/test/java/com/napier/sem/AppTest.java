package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }
    @Test
    void printSalaries()
    {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee emp = new Employee();

        // Use setter methods instead of direct field access
        emp.setEmp_no(1);
        emp.setFirst_name("Kevin");
        emp.setLast_name("Chalmers");
        emp.setTitle("Engineer");
        emp.setSalary(55000);
        employees.add(emp);
        app.printSalaries(employees);
    }
}