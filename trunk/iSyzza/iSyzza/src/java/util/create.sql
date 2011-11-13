-- TABELA CLIENTE

create table cliente(
	id_cliente number(4),
	nome varchar2(30),
	email varchar2(50),
	senha varchar2(32),
	salt number(12),
	qtd_pizzas number(4),
	telefone varchar2(20),
	endereco varchar2(100),
	cpf varchar2(11),
	data_cadastro date,
	constraint pk_cliente primary key(id_cliente)
);

-- TABELA PEDIDO

create table pedido(
	id_pedido number(4),
	data_pedido date,
	data_pronta date,
	data_entrega date,
	id_cliente number(4),
	constraint pk_pedido primary key(id_pedido)
);

-- TABELA ADICIONAL

create table adicional(
	id_adicional number(4),
	nome varchar2(20),
	preco number(4),
	constraint pk_adicional primary key(id_adicional)
);

-- TABELA PEDIDO_ADICIONAL

create table pedido_adicional(
	id_adicional number(4),
	id_pedido number(4),
	qtd number(4),
	constraint pk_pedido_adicional primary key(id_adicional, id_pedido)
);

-- TABELA PIZZA

create table pizza(
	id_pizza number(4),
	qtd number(4),
	id_tamanho number(4),
	id_borda number(4),
	id_pedido number(4),
	constraint pk_pizza primary key(id_pizza)
);

-- TABELA TAMANHO

create table tamanho(
	id_tamanho number(4),
	nome varchar2(15),
	preco number(4),
	constraint pk_tamanho primary key(id_tamanho)
);

-- TABELA BORDA

create table borda(
	id_borda number(4),
	nome varchar2(15),
	preco number(4),
	constraint pk_borda primary key(id_borda)
);

-- TABELA SABOR

create table sabor(
	id_sabor number(4),
	nome varchar2(20),
	preco number(4),
	constraint pk_sabor primary key(id_sabor)
);

-- TABELA PIZZA_SABOR

create table pizza_sabor(
	id_pizza number(4),
	id_sabor number(4),
	constraint pk_pizza_sabor primary key(id_pizza, id_sabor)
);
	
-- ADICIONANDO FOREIGN KEYS

alter table pedido add( 
	constraint fk_cliente foreign key(id_cliente) references cliente(id_cliente)
);
alter table pedido_adicional add(
	constraint fk_pedido_adicional foreign key(id_pedido) references pedido(id_pedido),
	constraint fk_adicional foreign key(id_adicional) references adicional(id_adicional)
);
alter table pizza add(
	constraint fk_pedido_pizza foreign key(id_pedido) references pedido(id_pedido),
	constraint fk_tamanho foreign key(id_tamanho) references tamanho(id_tamanho),
	constraint fk_borda foreign key(id_borda) references borda(id_borda)
);
alter table pizza_sabor add(
	constraint fk_pizza_sabor foreign key(id_pizza) references pizza(id_pizza),
	constraint fk_sabor foreign key(id_sabor) references sabor(id_sabor)
);

-- SEQUENCES

create sequence s_cliente increment by 1 start with 1 nocache;
create sequence s_pedido increment by 1 start with 1 nocache;
create sequence s_adicional increment by 1 start with 1 nocache;
create sequence s_pizza increment by 1 start with 1 nocache;
create sequence s_sabor increment by 1 start with 1 nocache;
create sequence s_tamanho increment by 1 start with 1 nocache;
create sequence s_borda increment by 1 start with 1 nocache;





