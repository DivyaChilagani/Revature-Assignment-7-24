package com.revature.W2day04.TASK1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeDAO {
//    private Employee employee;
    private static final String db_url = "jdbc:mysql://localhost:3306/rev_practice";
    private static final String user = "root";
    private static final String password = "Divya#123";

    //read all employees
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String query = "SELECT * FROM employee;";

        try(Connection connection = DriverManager.getConnection(db_url, user, password);
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {
                int employeeId = rs.getInt("employeeId");
                String employeeName = rs.getString("employeeName");
                String employeeEmail = rs.getString("email");
                String designation = rs.getString("designation");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");

                Employee employee = new Employee(employeeId, employeeName, employeeEmail, designation, department, salary);
                list.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // read employee by employeeId
    public Employee getEmployeeById(int employeeId) {
        String query = "SELECT * FROM employee WHERE employeeId = ?;";
        Employee employee = null;

        try (Connection connection = DriverManager.getConnection(db_url, user, password);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt(1));
                employee.setEmployeeName(rs.getString(2));
                employee.setEmail(rs.getString(3));
                employee.setDesignation(rs.getString(4));
                employee.setDepartment(rs.getString(5));
                employee.setSalary(rs.getDouble(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    //update designation
    public void updateDesignation(String newDesignation, int employeeId) {
        String query = "UPDATE employee SET designation = ? WHERE employeeId = ?;";

        try (Connection connection = DriverManager.getConnection(db_url, user, password);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, newDesignation);
            ps.setInt(2, employeeId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee with ID "+employeeId+" updated successfully.");
            } else {
                System.out.println("No Employee with ID "+employeeId+" to update.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //delete employee
    public boolean deleteEmployee(int employeeId) {
        String query = "DELETE FROM employee WHERE employeeId = ?;";

        try (Connection connection = DriverManager.getConnection(db_url, user, password);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, employeeId);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //count of employees
    public int totalEmployees() {
        String query = "SELECT COUNT(*) AS TotalEmployees FROM employee;";
        int count = 0;

        try (Connection connection = DriverManager.getConnection(db_url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if(rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public HashMap<String, Integer> employeesInDept() {
        String query = "SELECT department, COUNT(*) AS EmployeeCount FROM employee GROUP BY department;";
        HashMap<String, Integer> deptMap = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(db_url, user, password);
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String department = rs.getString("department");
                int employeeCount = rs.getInt("EmployeeCount");
                deptMap.put(department, employeeCount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deptMap;
    }

    public void insertEmployeeUsingProcedure(int employeeId, String employeeName, String email, String department, String designation, double salary) {
        String callProcedure = "{CALL insertIntoEmployee(?, ?, ?, ?, ?, ?)};";

        try (Connection connection = DriverManager.getConnection(db_url, user, password);
            CallableStatement cs = connection.prepareCall(callProcedure)) {

            cs.setInt(1, employeeId);
            cs.setString(2, employeeName);
            cs.setString(3, email);
            cs.setString(4, department);
            cs.setString(5, designation);
            cs.setDouble(6, salary);

            cs.execute();
            System.out.println("Employee inserted successfully using stored procedure.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
