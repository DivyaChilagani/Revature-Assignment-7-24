create database rev_practice;
use rev_practice;

create table employee (employeeId int primary key,
						employeeName varchar(255) not null,
                        email varchar(350) unique,
                        department varchar(255) not null,
                        designation varchar(50) not null,
                        salary double check(salary > 0));

insert into employee (employeeId, employeeName, email, department, designation, salary)
values (101, 'Sindhu', 'sindhu1425@gmail.com', 'IT', 'Developer', 40000),
		(102, 'John', 'john24@gmail.com', 'Marketing', 'Marketing executive', 30000),
		(103, 'James', 'james12@gmail.com', 'IT', 'Data Engineer', 45000),
		(104, 'Sam', 'sam14@gmail.com', 'Education', 'Assistant professor', 35000),
		(105, 'Meera', 'meera425@gmail.com', 'Banking', 'Manager', 25000);


DELIMITER //

CREATE PROCEDURE InsertIntoEmployee (
	IN employeeId int,
    IN employeeName VARCHAR(255),
    IN email Varchar(350),
    IN department varchar(255),
	IN designation varchar(50),
    IN salary double
)

BEGIN
	INSERT INTO employee (employeeId, employeeName, email, department, designation, salary)
    VALUES (employeeId, employeeName, email, department, designation, salary);
END //

DELIMITER ;