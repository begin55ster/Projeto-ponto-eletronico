CREATE TABLE empresa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cnpj VARCHAR(20),
    id_funcionario BIGINT(20) NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO empresa (nome,cnpj,id_funcionario) values ('Basis Tecnologia', '21584.3562.55/0001','1');