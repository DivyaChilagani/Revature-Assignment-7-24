package com.revature.Service;

import com.revature.DTO.Employee;
import com.revature.DAO.EmployeeDAO;

import java.util.HashMap;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public void getAllEmp() {
        List<Employee> employeesList = employeeDAO.getAllEmployees();

        for (Employee employee : employeesList) {
            System.out.println(employee);
        }
    }

    public Employee findEmpById(int empId) {
        return employeeDAO.getEmployeeById(empId);
    }

    public void updateDes(String designation, int employeeId) {
        Employee findEmp = employeeDAO.getEmployeeById(employeeId);
        if (findEmp != null) {
            employeeDAO.updateDesignation(designation, employeeId);
        } else {
            System.out.println("Employee not found with ID "+ employeeId+" to update.");
        }

    }

    public int countEmployees() {
        return employeeDAO.totalEmployees();
    }

    public HashMap<String, Integer> getEmployeesInEachDepartment() {
        return employeeDAO.employeesInDept();
    }

    public boolean deleteEmp(int employeeId) {
        return employeeDAO.deleteEmployee(employeeId);
    }

    public void addEmployee(int id, String name, String email, String designation, String department, double salary) {
        employeeDAO.insertEmployeeUsingProcedure(id, name, email, designation, department, salary);
    }
}
