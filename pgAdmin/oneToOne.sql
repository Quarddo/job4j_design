create table identifier(
	id serial primary key, 
	seria int, 
	number int
);
	
create table car(
	id serial primary key, 
	name varchar(255), 
	identifier_id int references identifier(id) unique
);

insert into identifier(seria, number) values (12, 3456);
insert into car(name) values ('Lexus');