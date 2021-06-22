create database Class;

create table Student(
id int primary key,
name varchar(15),
marks int,
standard int
);

insert into Student values(1, "Sonam", 87, 2),
(2, "Neha", 76, 7),
(3, "komal", 67, 5),
(4, "Priyanka", 74, 7),
(5, "Pooja", 79, 6),
(6, "sonali", 49, 8),
(7, "kavita", 90, 3),
(8, "Tina", 65, 5),
(9, "Reena", 57, 3);

select * from Student;

SELECT name, MAX(marks) AS topper
  FROM student
 WHERE marks < (SELECT MAX(marks)
                 FROM student); 

select count(id), standard from student group by standard;