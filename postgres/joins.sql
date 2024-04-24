-- create table student(id int primary key, name varchar(255)not null , grade varchar(10) not null , department varchar(255) not null );
-- insert into student(id , name , grade , department)values(1,'aa',9,'IT');
-- insert into student(id , name,  grade, department) values(2,'ab',8,'CSE'),(3,'ac',8,'CSE'),(4,'ad',9,'ECE'),(5,'bb',7,'MECH');
-- select * from student inner join rowNumber on student.id = rowNumber.id; 
-- select * from student left join rowNumber on student.id = rowNumber.id;
-- select * from student right join rowNumber on student.id = rowNumber.id; 
-- select * from student full join rowNumber on student.id = rowNumber.id;

--joins with generate series 
-- create table sales(sno int , prod varchar(255) , unit int , saleDate date);
-- insert into sales(sno , prod , unit , saleDate) values(1 , 'juice' , 100 ,'2024-03-01'),(2, 'cake' , 20 , '2024-03-02') , (3, 'bread' , 40, '2024-03-03'),(4 , 'egg' , 200 ,'2024-03-05'),(5, 'chocolate' , 80 ,'2024-03-07'),(6 , 'sauce' , 12 ,'2024-03-09');
-- select * from sales;
-- select * from generate_series('2024-03-01'::timestamp , '2024-03-10' , '24hours') as genDate where genDate not in(select saleDate from sales);
-- select dates , case when unit is null then 0 else unit end as unit from generate_series('2024-03-01'::timestamp , '2024-03-10' , '1day') as dates left join sales on dates = saledate;
select dates , coalesce(unit , 0) from generate_series('2024-03-01'::timestamp , '2024-03-10' , '24hours') as dates left join sales on dates = saledate order by dates;