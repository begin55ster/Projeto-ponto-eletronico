CREATE TABLE ponto (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_funcionario BIGINT(20) NOT NULL,
    data DATE,
    entrada1 TIME,
    saida1 TIME,
    entrada2 TIME,
    saida2 TIME,
    entrada3 TIME,
    saida3 TIME,
    fim_expediente TIME,
    restante_dia TIME,
    almoco TIME,
    tempo_trabalhado TIME,
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO ponto (id_funcionario,data,entrada1,saida1,entrada2,saida2,entrada3,saida3,fim_expediente,restante_dia,almoco,tempo_trabalhado) 
values ('1', NOW(),CURTIME(),CURTIME(),CURTIME(),CURTIME(),CURTIME(),CURTIME(),CURTIME(),CURTIME(),CURTIME(),CURTIME());