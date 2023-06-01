CREATE TABLE public.user (
	codigo SERIAL PRIMARY KEY,
    	cpf CHAR(11),
    	email VARCHAR(50),
    	full_name VARCHAR(150),
	senha VARCHAR(50),
    	datanascimento DATE
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

CREATE TABLE public.publicacao (
    codigo SERIAL PRIMARY KEY,
    urlvideo VARCHAR(255),
    hashtags VARCHAR(50),
    titulo VARCHAR(100),
    classeid INTEGER,
    visualizacoes INTEGER,
    likes INTEGER,
    dislikes INTEGER,
    datapostagem DATE,
    FOREIGN KEY (classeid) REFERENCES "classe"(codigo)
);

CREATE TABLE public.userClasse (
    codigo SERIAL PRIMARY KEY,
    userid INTEGER,
    FOREIGN KEY (userid) REFERENCES "user"(codigo),
    classeid INTEGER,
    FOREIGN KEY (classeid) REFERENCES "classe"(codigo)
);

CREATE TABLE public.comentarios (
    codigo SERIAL PRIMARY KEY,
    userid INTEGER,
    FOREIGN KEY (userid) REFERENCES "user"(codigo),
    publicacaoid INTEGER,
    FOREIGN KEY (publicacaoid) REFERENCES "publicacao"(codigo),
    likes INTEGER,
    dislikes INTEGER,
    descricao TEXT
);

CREATE TABLE public.userrating (
    codigo SERIAL PRIMARY KEY,
    userid INTEGER,
    FOREIGN KEY (userid) REFERENCES "user"(codigo),
    publicacaoid INTEGER,
    FOREIGN KEY (publicacaoid) REFERENCES "publicacao"(codigo),
	rating INTEGER CHECK (rating >= 1 AND rating <= 5)
);