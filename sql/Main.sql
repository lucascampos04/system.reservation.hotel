\connect ManagementHoteis;
select * from clientes;

delete from clientes
where id IN (
	select id
	from clientes
	where plano_id is null
);

select email, plano_id from clientes;

select * from clientes;
select email from clientes;
select * from plano_entity;

delete from plano_entity where pacote is null;

delete from clientes 
where plano_id in (
	select c.plano_id 
	from clientes c
	left join plano_entity p on c.plano_id = p.id
	where p.pacote is null
);