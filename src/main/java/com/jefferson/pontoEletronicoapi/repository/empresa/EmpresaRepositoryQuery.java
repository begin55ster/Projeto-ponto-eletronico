package com.jefferson.pontoEletronicoapi.repository.empresa;

import java.util.List;

import com.jefferson.pontoEletronicoapi.model.Empresa;
import com.jefferson.pontoEletronicoapi.repository.filter.EmpresaFilter;

public interface EmpresaRepositoryQuery {
	
	public List<Empresa> filtrar(EmpresaFilter empresaFilter);

}
