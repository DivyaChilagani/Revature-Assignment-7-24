package com.revature.W2day04.TASK1;

import java.util.HashMap;
import java.util.Map;

public class EmployeeMain {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();

        //get employees list
        service.getAllEmp();

        //find employee by id
        Employee emp = service.findEmpById(103);
        if (emp != null) {
            System.out.println(emp);
        } else {
            System.out.println("Employee not found.");
        }

        //count of employees in each department
        HashMap<String, Integer> departmentCounts = service.getEmployeesInEachDepartment();

        for (Map.Entry<String, Integer> entry : departmentCounts.entrySet()) {
            System.out.println("Department: "+ entry.getKey()+", Employees: "+ entry.getValue());
        }

        //delete employee
        boolean deleted = service.deleteEmp(101);
        if (deleted) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("No employee found with given ID.");
        }

        //insert into employee
        service.addEmployee(106, "Karthikeya", "itsmeKarthikeya@gmail.com", "Software", "Java Developer", 35000);

        //count employees in table
        int total = service.countEmployees();
        System.out.println("Total number of employees: "+ total);

        // update employee designation
        service.updateDes("Senior Developer", 103);


    }
}
