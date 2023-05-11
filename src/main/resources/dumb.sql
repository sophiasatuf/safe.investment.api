CREATE TABLE public.user (
	codigo SERIAL PRIMARY KEY,
    CPF CHAR(11),
    email VARCHAR(50),
    full_name VARCHAR(250),
	senha VARCHAR(50),
    age INTEGER
);