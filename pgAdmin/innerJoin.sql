create table information(
	id serial primary key,
	role varchar(255), 
	club varchar(255));
	
create table player(
	id serial primary key, 
	name varchar(255),
	information_id int references information(id));
	
insert into information(role, club) values ('нападающий', 'Челси');
insert into information(role, club) values ('защитник', 'Ливерпуль');
insert into information(role, club) values ('вратарь', 'Манчестер юнайтед');
insert into information(role, club) values ('нападающий', 'Лестер');

insert into player(name, information_id) values ('Вернер','1');
insert into player(name, information_id) values ('ВанДейк','2');
insert into player(name, information_id) values ('ДеХеа', '3');
insert into player(name, information_id) values ('Варди','4');
insert into player(name) values ('Хавертц');

select * from player 
join information i 
on player.information_id = i.id;

select p.name, i.role, i.club
from player as p
join information as i 
on p.information_id = i.id;

select p.name as Имя, i.role as Амплуа, i.club as Клуб
from player as p 
join information as i
on p.information_id = i.id and i.role like 'нап%';