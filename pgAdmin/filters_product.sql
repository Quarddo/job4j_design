create table type(
	id serial primary key, 
	name varchar(255));
	
create table product(
	id serial primary key, 
	name varchar(255),
	expired_date date,
	price float,
	type_id int references type(id));

insert into type(name) values ('Сыр'), ('Молоко'), ('Мороженое');

insert into product(name, expired_date, price, type_id) values 
('Моцарелла', '2022.02.01', 300, 1),
('Пармезан', '2022.02.02', 250, 1),
('Сулугуни', '2022.02.01', 350, 1),
('Коровье', '2022.01.20', 150, 2),
('Козье', '2022.01.21', 130, 2),
('Топленое', '2022.01.19', 145, 2),
('Мороженое пломбир', '2022.02.05', 280, 3),
('Мпороженое шоколадное', '2022.02.04', 550, 3),
('Мороженное ванильное', '2022.02.04', 380, 3);

select * from product p
join type t
on p.type_id = t.id
where t.name = 'Сыр';

select * from product p
join type t
on p.type_id = t.id
and p.name like '%жен%';

select * from product p
join type t
on p.type_id = t.id
and p.expired_date < current_date;

select * from product p
join type t 
on p.type_id = t.id
and price = (select max(price) from product);

select t.name as Имя_типа, count(p.type_id) as Количество
from product p
join type t 
on p.type_id = t.id
group by t.name;

select * from product p
join type t 
on p.type_id = t.id
where t.name = 'Сыр' 
or t.name = 'Молоко';

select t.name as Имя_типа, count(p.type_id) as Количество
from product p
join type t 
on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;

select * from product p
join type t 
on p.type_id = t.id;