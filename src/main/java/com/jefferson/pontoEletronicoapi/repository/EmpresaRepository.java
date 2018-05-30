package com.jefferson.pontoEletronicoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jefferson.pontoEletronicoapi.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	

}
