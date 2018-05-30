CREATE TABLE funcionario (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    pis VARCHAR(30),
    email VARCHAR(35),
    id_empresa BIGINT(20) NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES empresa(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO funcionario (nome,cpf,pis,email,id_empresa) values ('Jefferson Goncalves dos Santos', '920-456-001-06','02156.32145.25125','begin55ster@gmail.com','1');
INSERT INTO funcionario (nome,cpf,pis,email,id_empresa) values ('Maria da Silva Matos', '958-654-258-96','02156.32568.32154','maria@gmail.com','1');
INSERT INTO funcionario (nome,cpf,pis,email,id_empresa) values ('Raimundo de Oliveira Junior', '586-654-695-55','02156.87444.65212','raimundo@gmail.com','1');
INSERT INTO funcionario (nome,cpf,pis,email,id_empresa) values ('Sebastião Marques de Olinda', '854-954-856-88','02156.33325.11245','sebastiao@gmail.com','1');
INSERT INTO funcionario (nome,cpf,pis,email,id_empresa) values ('Joana Maria de Paula', '254-632-569-87','02156.84215.21111','joana@gmail.com','1');
