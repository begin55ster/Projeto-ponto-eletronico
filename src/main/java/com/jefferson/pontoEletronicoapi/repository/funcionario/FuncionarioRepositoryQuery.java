package com.jefferson.pontoEletronicoapi.repository.funcionario;

import java.util.List;

import com.jefferson.pontoEletronicoapi.model.Funcionario;
import com.jefferson.pontoEletronicoapi.repository.filter.FuncionarioFilter;

public interface FuncionarioRepositoryQuery {
	
	public List<Funcionario> filtrar(FuncionarioFilter funcionarioFilter);

}
