create table occupation(
    id serial primary key,
    name varchar(255)
);
	
create table citizen(
    id serial primary key,
    name varchar(255),
    occupation_id int references occupation(id)
);
	
insert into occupation(name) values ('athlete');
insert into citizen(name, occupation_id) VALUES ('Max', 1);

select * from citizen;

select * from occupation where id in (select id from citizen);