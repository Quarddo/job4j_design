select p.name as Имя, i.role as Амплуа, i.club as Клуб
from player as p 
join information as i
on p.information_id = i.id and i.role like 'нап%';