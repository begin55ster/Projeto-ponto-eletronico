package com.jefferson.pontoEletronicoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jefferson.pontoEletronicoapi.model.Empresa;
import com.jefferson.pontoEletronicoapi.repository.empresa.EmpresaRepositoryQuery;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>, EmpresaRepositoryQuery {
	
	

}
