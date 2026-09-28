package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class App {

    private Connection con = null;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        String dbLocation = System.getenv("DB_LOCATION");

        if (dbLocation == null || dbLocation.isEmpty()) {
            // When running in Docker, use the container name "db"
            // and the internal MySQL port 3306
            dbLocation = "db:33060";
        }

        // Connect to the employees database
        String url = "jdbc:mysql://" + dbLocation
                + "/employees?useSSL=false&allowPublicKeyRetrieval=true";

        String user = "root";
        String password = "example";

        int retries = 10;

        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");

            try {
                Thread.sleep(5000);

                con = DriverManager.getConnection(url, user, password);

                System.out.println("Successfully connected");
                break;

            } catch (SQLException sqle) {
                System.out.println(
                        "Failed to connect to database attempt " + i
                );
                System.out.println(sqle.getMessage());

            } catch (InterruptedException ie) {
                System.out.println(
                        "Thread interrupted? Should not happen."
                );
            }
        }
    }

    public Employee getEmployee(int ID) {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT emp_no, first_name, last_name "
                            + "FROM employees "
                            + "WHERE emp_no = " + ID;

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {

                Employee emp = new Employee();

                emp.setEmp_no(rset.getInt("emp_no"));
                emp.setFirst_name(rset.getString("first_name"));
                emp.setLast_name(rset.getString("last_name"));

                return emp;

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");

            return null;
        }
    }

    public void displayEmployee(Employee emp) {
        if (emp != null) {

            System.out.println(
                    emp.getEmp_no() + " "
                            + emp.getFirst_name() + " "
                            + emp.getLast_name() + "\n"
                            + emp.getTitle() + "\n"
                            + "Salary: " + emp.getSalary() + "\n"
                            + emp.getDept_name() + "\n"
                            + "Manager: " + emp.getManager() + "\n"
            );
        }
    }

    public void disconnect() {
        if (con != null) {

            try {
                con.close();
                System.out.println("Disconnected from database");

            } catch (Exception e) {
                System.out.println(
                        "Error closing connection to database"
                );
            }
        }
    }

    public static void main(String[] args) {

        App a = new App();

        a.connect();

        // Get all current employee salaries
        ArrayList<Employee> employees = a.getAllSalaries();

        if (employees != null) {

            System.out.println(
                    "Number of employees: " + employees.size()
            );

            // Print employee salary information
            a.printSalaries(employees);
        }

        a.disconnect();
    }

    /**
     * Gets all current employee salaries.
     *
     * @return ArrayList containing Employee objects
     */
    public ArrayList<Employee> getAllSalaries() {

        try {

            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT employees.emp_no, "
                            + "employees.first_name, "
                            + "employees.last_name, "
                            + "salaries.salary "
                            + "FROM employees, salaries "
                            + "WHERE employees.emp_no = salaries.emp_no "
                            + "AND salaries.to_date = '9999-01-01' "
                            + "ORDER BY employees.emp_no ASC";

            ResultSet rset = stmt.executeQuery(strSelect);

            // Specify that the ArrayList contains Employee objects
            ArrayList<Employee> employees = new ArrayList<>();

            while (rset.next()) {

                Employee emp = new Employee();

                emp.setEmp_no(
                        rset.getInt("emp_no")
                );

                emp.setFirst_name(
                        rset.getString("first_name")
                );

                emp.setLast_name(
                        rset.getString("last_name")
                );

                emp.setSalary(
                        rset.getInt("salary")
                );

                employees.add(emp);
            }

            return employees;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");

            return null;
        }
    }

    /**
     * Prints a list of employees and their salaries.
     *
     * @param employees The list of employees to print
     */
    public void printSalaries(ArrayList<Employee> employees) {

        if (employees == null || employees.isEmpty()) {

            System.out.println("No employees found.");

            return;
        }

        System.out.println(
                String.format(
                        "%-10s %-15s %-20s %-8s",
                        "Emp No",
                        "First Name",
                        "Last Name",
                        "Salary"
                )
        );

        System.out.println(
                "--------------------------------------------------------"
        );

        for (Employee emp : employees) {

            String emp_string =
                    String.format(
                            "%-10s %-15s %-20s %-8s",
                            emp.getEmp_no(),
                            emp.getFirst_name(),
                            emp.getLast_name(),
                            emp.getSalary()
                    );

            System.out.println(emp_string);
        }
    }
}