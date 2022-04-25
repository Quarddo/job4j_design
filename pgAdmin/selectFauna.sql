select * from fauna where name = 'fish';
select * from fauna where avg_age >= 2000 and avg_age <=5000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1850';