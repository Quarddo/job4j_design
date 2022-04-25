create table information(
	id serial primary key,
	role varchar(255), 
	club varchar(255));
	
create table player(
	id serial primary key, 
	name varchar(255),
	information_id int references information(id));