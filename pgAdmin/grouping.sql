create table devices(
	id serial primary key, 
	name varchar(255), 
	price float);
	
create table people(
	id serial primary key, 
	name varchar(255));
	
create table devices_people(
	id serial primary key, 
	devices_id int references devices(id),
	people_id int references people(id));
	
insert into devices(name, price) values ('phone', 3000), ('TV',5500), ('laptop', 6000), ('stove', 1500);

insert into people(name) values ('Alex'), ('Mark'), ('Den'), ('Max');

insert into devices_people(devices_id, people_id) values 
(1, 1),
(2, 1), (2, 2),
(3, 1), (3, 2), (3, 3),
(4, 1), (4, 2), (4, 3), (4, 4);

select avg(price) from devices;

select p.name as Имя, avg(d.price) as СредняяЦена
from devices_people dp
join devices d on dp.devices_id = d.id
join people p on dp.people_id = p.id
group by p.name;

select p.name as Имя, avg(d.price) as СредняяЦена
from devices_people dp
join people p on dp.people_id = p.id
join devices d on dp.devices_id = d.id
group by p.name
having avg(d.price) > 3800;
