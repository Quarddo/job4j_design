create table user_info(
    id serial primary key,
    name varchar(255),
    age int,
    weight float8,
    married bool);
	
insert into user_info(name, age, weight, married)
values('Max', 26, 74.5, true);

update user_info set weight = 73.5;

delete from user_info;