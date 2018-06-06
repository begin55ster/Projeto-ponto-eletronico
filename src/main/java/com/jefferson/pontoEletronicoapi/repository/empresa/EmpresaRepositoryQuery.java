package com.jefferson.pontoEletronicoapi.repository.empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jefferson.pontoEletronicoapi.model.Empresa;
import com.jefferson.pontoEletronicoapi.repository.filter.EmpresaFilter;

public interface EmpresaRepositoryQuery {
	
	public Page<Empresa> filtrar(EmpresaFilter empresaFilter, Pageable pageable);

}
