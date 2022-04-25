select p.name, i.role, i.club
from player as p
join information as i 
on p.information_id = i.id;