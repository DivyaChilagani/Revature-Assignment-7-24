package com.revature.Main;

import com.revature.DTO.Employee;
import com.revature.Service.EmployeeService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeMain {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        Scanner sc = new Scanner(System.in);

        String choice = "y";

        while (choice.equalsIgnoreCase("y")) {
            System.out.println("Select an option to perform :");
            System.out.println("1. Get all employees details");
            System.out.println("2. Get an employee detail by employeeId");
            System.out.println("3. Get count of employees");
            System.out.println("4. Get count of employees in each department");
            System.out.println("5. Delete an employee with employeeId");
            System.out.println("6. Update designation of an employee with employeeId");
            System.out.println("7. Insert an employee");

            int choose = sc.nextInt();

            switch (choose) {
                case 1:
                    service.getAllEmp();
                    break;
                case 2:
                    System.out.println("Enter employeeId");
                    int empId = sc.nextInt();
                    Employee emp = service.findEmpById(empId);
                    if (emp != null) {
                        System.out.println(emp);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 3:
                    int total = service.countEmployees();
                    System.out.println("Total number of employees: "+ total);
                    break;
                case 4:
                    HashMap<String, Integer> departmentCounts = service.getEmployeesInEachDepartment();

                    for (Map.Entry<String, Integer> entry : departmentCounts.entrySet()) {
                        System.out.println("Department: "+ entry.getKey()+", Employees: "+ entry.getValue());
                    }
                    break;
                case 5:
                    System.out.println("Enter employeeId");
                    int emp1Id = sc.nextInt();
                    boolean deleted = service.deleteEmp(emp1Id);
                    if (deleted) {
                        System.out.println("Employee deleted successfully.");
                    } else {
                        System.out.println("No employee found with given ID.");
                    }
                    break;
                case 6:
                    System.out.println("Enter new designation and employeeId");
                    String designation = sc.nextLine();
                    int emp2id = sc.nextInt();
                    service.updateDes(designation, emp2id);
                    break;
                case 7:
                    System.out.println("Enter the following details: ");
                    System.out.println("EmployeeId = ");
                    int employeeId = sc.nextInt();
                    System.out.println("EmployeeName = ");
                    String employeeName = sc.nextLine();
                    System.out.println("email = ");
                    String email = sc.nextLine();
                    System.out.println("Designation = ");
                    String Designation = sc.nextLine();
                    System.out.println("Department = ");
                    String department = sc.nextLine();
                    System.out.println("Salary = ");
                    double salary = sc.nextDouble();
                    service.addEmployee(employeeId, employeeName, email, Designation, department, salary);
                    break;
                default:
                    System.out.println("Invalid Request!!!");
            }

            System.out.println("/\n Do you want to perform another operation? (y/n)");
            sc.nextLine();
            choice = sc.nextLine();
        }

        System.out.println("ThankYou!!!");
        sc.close();


    }
}
