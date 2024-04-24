-- create table rowNumber(id int, name varchar(255), subject varchar(255), grade int);
-- insert into rowNumber(id,name,subject,grade) values(1,'demo','cs',9),(2,'demo2','maths',7),
-- (3,'a2','sci',8),(4,'neo','cs',7),(5,'nemo','sci',9),(6,'raa','maths',6),(7,'dinesh','cs',8),(8,'san','maths',6),(9,'huss','sci',10),(10,'don','cs',9);
-- select * from rowNumber;
-- select *, row_number() over(partition by subject ) from rowNumber;
-- select generate_series(9,100,9);
-- select generate_series('19-02-2024'::timestamp , '12-05-2024' , '7days');
select *, dense_rank() over(partition by subject order by grade desc ) , rank() over(partition by subject order by grade desc) from rowNumber;
