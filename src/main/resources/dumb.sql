CREATE TABLE public.user (
	codigo SERIAL PRIMARY KEY,
    	CPF CHAR(11),
    	email VARCHAR(50),
    	full_name VARCHAR(250),
	senha VARCHAR(50),
    	age INTEGER
);

CREATE TABLE public.professor (
    codigo SERIAL PRIMARY KEY,
    avggrade NUMERIC(2,2),
    userid INTEGER,
    stamp BOOLEAN,
    FOREIGN KEY (userid) REFERENCES "user"(codigo)
);

CREATE TABLE public.classe (
	codigo SERIAL PRIMARY KEY,
	titulo VARCHAR(100),
	descricao TEXT,
	professorid INTEGER,
	FOREIGN KEY (professorid) REFERENCES "professor"(codigo)
);