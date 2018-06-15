CREATE TABLE usuario (
	id BIGINT(20) PRIMARY KEY,
	usernome VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	id BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO usuario (id, userNome, senha) values (1, 'admin', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (id, userNome, senha) values (2, 'jefferson.santos', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permissao (id, descricao) values (1, 'ROLE_CADASTRAR_EMPRESA');
INSERT INTO permissao (id, descricao) values (2, 'ROLE_PESQUISAR_EMPRESA');
INSERT INTO permissao (id, descricao) values (3, 'ROLE_REMOVER_EMPRESA');

INSERT INTO permissao (id, descricao) values (4, 'ROLE_CADASTRAR_FUNCIONARIO');
INSERT INTO permissao (id, descricao) values (5, 'ROLE_REMOVER_FUNCIONARIO');
INSERT INTO permissao (id, descricao) values (6, 'ROLE_PESQUISAR_FUNCIONARIO');

INSERT INTO permissao (id, descricao) values (7, 'ROLE_CADASTRAR_PONTO');
INSERT INTO permissao (id, descricao) values (8, 'ROLE_REMOVER_PONTO');
INSERT INTO permissao (id, descricao) values (9, 'ROLE_PESQUISAR_PONTO');
INSERT INTO permissao (id, descricao) values (10, 'ROLE_ATUALIZAR_PONTO');

-- admin
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 8);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 9);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 10);

-- maria jefferson.santos
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 9);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 10);