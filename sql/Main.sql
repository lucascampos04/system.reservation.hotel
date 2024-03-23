SELECT column_name, data_type
FROM information_schema.columns
WHERE table_name = 'employees';

select column_name, data_type
from information_schema.columns
where table_name = 'reservas_entity';

select * from clientes;
select * from plano_entity;

SELECT c.nome, c.email, c.cpf,
       p.plano, p.valor
FROM clientes c
JOIN plano_entity p ON c.plano_id = p.id;
