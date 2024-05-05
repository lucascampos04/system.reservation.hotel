delete from clientes;
delete from reservations;
delete from login_dados;

SELECT table_schema, table_name
FROM information_schema.tables
WHERE table_type = 'BASE TABLE' AND table_schema NOT IN ('pg_catalog', 'information_schema');


SELECT 
    clientes.nome AS nome_cliente,
    clientes.email AS email_cliente,
	clientes.roles AS role_cliente,
    dados_login.login AS login,
    dados_login.type_acess AS tipo_acesso,
	dados_login.type_login AS tipo_login,
	dados_login.password as senha,
    plano_entity.plano AS nome_plano,
    plano_entity.valor AS valor_plano
FROM 
    clientes
INNER JOIN 
    dados_login ON clientes.id = dados_login.id_cliente
INNER JOIN 
    plano_entity ON clientes.plano_id = plano_entity.id
WHERE
    clientes.nome IS NOT NULL
    AND clientes.email IS NOT NULL
    AND dados_login.login IS NOT NULL
    AND dados_login.type_acess IS NOT NULL
    AND dados_login.type_login IS NOT NULL
    AND dados_login.password IS NOT NULL
    AND plano_entity.plano IS NOT NULL
    AND plano_entity.valor IS NOT NULL;

select id, nome, email, roles, status
	from clientes;

select type_acess, id_cliente, login, password, type_login
	from dados_login;

select * from plano_entity;
select * from reservations;
