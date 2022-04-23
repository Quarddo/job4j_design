create table humans(
     id serial primary key,
     name varchar(255)
 );
 
 create table insurances(
     id serial primary key,
     name varchar(255)
 );
 
 create table humans_insurances(
     id serial primary key,
     human_id int references humans(id),
     insurace_id int references insurances(id)
 );
 
insert into humans(name) values ('Max');
insert into humans(name) values ('Anton');
insert into humans(name) values ('Roman');

insert into insurances(name) values ('LIfe');
insert into insurances(name) values ('Health');
insert into insurances(name) values ('Car');

insert into humans_insurances(human_id, insurace_id) values (1, 1);
insert into humans_insurances(human_id, insurace_id) values (1, 2);
insert into humans_insurances(human_id, insurace_id) values (1, 3);
insert into humans_insurances(human_id, insurace_id) values (2, 1);
insert into humans_insurances(human_id, insurace_id) values (2, 2);
insert into humans_insurances(human_id, insurace_id) values (3, 3);