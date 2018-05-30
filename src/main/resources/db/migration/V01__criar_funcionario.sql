CREATE TABLE funcionario (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    pis VARCHAR(30),
    email VARCHAR(35)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO funcionario (nome,cpf,pis,email) values ('Jefferson Goncalves', '920-456-001-06','02156.32145.25125','bgin55ster@gmail.com');
