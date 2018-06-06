package com.jefferson.pontoEletronicoapi.repository.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jefferson.pontoEletronicoapi.model.Funcionario;
import com.jefferson.pontoEletronicoapi.repository.filter.FuncionarioFilter;

public interface FuncionarioRepositoryQuery {
	
	public Page<Funcionario> filtrar(FuncionarioFilter funcionarioFilter, Pageable pageable);

}
