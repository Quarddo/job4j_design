create table roles(
	id serial primary key,
	name varchar(255));
	
create table users(
	id serial primary key,
	name varchar(255), 
	role_id int references roles(id));
		
create table rules(
	id serial primary key,
	name varchar(255));
	
create table role_ruls(
	id serial primary key,
	role_id int references roles(id), 
	rule_id int references rules(id));

create table state_item(
	id serial primary key, 
	status bool);
	
create table category_item(
	id serial primary key, 
	name varchar(255));

create table items(
	id serial primary key,
	name varchar(255),
	user_id int references users(id),
	state_id int references state_item(id),
	category_id int references category_item(id));

create table comments_items(
	id serial primary key,
	comment text, 
	item_id int references items(id));
	
create table attachs(
	id serial primary key, 
	name varchar(255), 
	item_id int references items(id)
);