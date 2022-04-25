insert into roles(name) values ('нападающий');
insert into roles(name) values ('защитник');
insert into roles(name) values ('вратарь');

insert into users(name, role_id) values ('Максим', 1);
insert into users(name, role_id) values ('Роман', 2);
insert into users(name, role_id) values ('Андрей', 3);

insert into rules(name) values ('забивать голы');
insert into rules(name) values ('остановить нападение');
insert into rules(name) values ('отражать мяч');
insert into rules(name) values ('защищать ворота');

insert into role_ruls(role_id, rule_id) values (1, 1);
insert into role_ruls(role_id, rule_id) values (2, 2);
insert into role_ruls(role_id, rule_id) values (2, 4);
insert into role_ruls(role_id, rule_id) values (3, 3);
insert into role_ruls(role_id, rule_id) values (3, 4);

insert into state_item(status) values (true);
insert into state_item(status) values (false);

insert into category_item(name) values ('очень важная');
insert into category_item(name) values ('важная');
insert into category_item(name) values ('средняя');

insert into items(name, user_id, state_id, category_id) 
            values ('забить гол в первом тайме', 1, 2, 3);
insert into items(name, user_id, state_id, category_id) 
            values ('забить победный гол', 1, 1, 1);
insert into items(name, user_id, state_id, category_id) 
            values ('не получить красную карточку', 2, 1, 2);
insert into items(name, user_id, state_id, category_id) 
            values ('не пропустить гол', 3, 1, 1);

insert into comments_items(comment, item_id) 
            values ('главное победить', 1);
insert into comments_items(comment, item_id) 
            values ('нужно больше бить по воротам', 2);
insert into comments_items(comment, item_id) 
            values ('меньше эмоций', 3);
insert into comments_items(comment, item_id) 
            values ('сохраняй бдительность', 4);

insert into attachs(name, item_id) values ('схема игры', 2);
insert into attachs(name, item_id) values ('схема игры', 3);
insert into attachs(name, item_id) values ('схема игры', 4);