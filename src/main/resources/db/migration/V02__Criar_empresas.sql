CREATE TABLE empresa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cnpj VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

INSERT INTO empresa (nome,cnpj) values ('Basis Tecnologia', '21584.3562.55/0001');
