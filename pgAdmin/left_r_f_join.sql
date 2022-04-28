create table departments(
    id serial primary key, 
    n_name varchar(255));

create table employees(
    id serial primary key,
    n_name varchar(255),
    department_id int references departments(id));
	
create table teens(
    id serial primary key,
     n_name varchar(255),
     gender varchar(255));

insert into departments(n_name) 
values ('Тороговли'), ('Снабжения'), ('Бухгалтерия'), ('Маркетинга');
insert into departments(n_name) 
values ('Управления'), ('Безопасности');


insert into employees(n_name, department_id)
values ('Антон', 1), ('Денис', 1), ('Владимир', 2), ('Максим', 4), ('Сергей', 4), ('Анна', 3), ('Даша', 1 );

insert into employees(n_name) values ('Валера');

insert into teens(n_name, gender)
values ('Антон', 'М'), ('Денис', 'М'), ('Анна', 'Ж'), ('Катя', 'Ж'), ('Максим', 'М'), ('Настя', 'Ж');

select * from employees e
left join departments d 
on e.department_id = d.id;

select * from employees e
right join departments d 
on e.department_id = d.id;

select * from employees e
full join departments d 
on e.department_id = d.id;

select * from departments d
left join employees e
on e.department_id = d.id
where e.n_name is null;

select * from departments d
left join employees e
on e.department_id = d.id
where e.n_name is not null;

select * from departments d
right join employees e
on e.department_id = d.id
where d.n_name is not null;

select t.n_name Имя, tn.n_name Имя
from teens t
cross join teens tn
where t.gender != tn.gender;