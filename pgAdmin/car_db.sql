create table body(
	id serial primary key,
	name varchar(255));

create table engine(
	id serial primary key,
	name varchar(255));

create table transmission(
	id serial primary key,
	name varchar(255));

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id), 
	engine_id int references engine(id),
	transmission_id int references transmission(id));
	
insert into body(name) values ('Седан'), ('Хэтчбэк'), ('Универсал'), ('Купе'), ('Минивен');

insert into engine(name) values ('Дизель'), ('Бензин'), ('Гибрид'), ('Электронный');

insert into transmission(name) values ('Ручная'), ('Автоматическая'), ('Роботизированная'), ('Полуавтоматическая');

insert into car(name, body_id, engine_id, transmission_id) values 
('Мазда', 1, 2, 3), ('Тайота', 3, 1, 2), ('Опель', 4, 3, 1),
('Лексус', 3, 4, 2), ('Ситроен', 2, 1, 1), ('Мерседес', 1, 2, 2);

select c.name Марка, b.name Кузов, e.name Двигатель, t.name Коробка_Передач  
from car c 
join body b on c.body_id = b.id
join engine e on c.engine_id = e.id
join transmission t on c.transmission_id = t.id;
   
select b.name Неиспользуемый_Кузов, c.name Марка
from body b 
left join car c 
on c.body_id = b.id
where c.body_id is null; 

select e.name Неиспользуемый_Двигатель, c.name Марка
from engine e 
left join car c 
on c.engine_id = e.id
where c.engine_id is null;

select t.name Неиспользуемая_Коробка, c.name Марка
from transmission t 
left join car c 
on c.transmission_id = t.id
where c.transmission_id is null; 
	