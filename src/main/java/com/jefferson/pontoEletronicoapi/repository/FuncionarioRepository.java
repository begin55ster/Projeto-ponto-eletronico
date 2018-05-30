package com.jefferson.pontoEletronicoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jefferson.pontoEletronicoapi.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	

}
