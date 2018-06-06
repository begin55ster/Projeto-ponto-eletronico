package com.jefferson.pontoEletronicoapi.repository.ponto;

import java.util.List;

import com.jefferson.pontoEletronicoapi.model.Ponto;
import com.jefferson.pontoEletronicoapi.repository.filter.PontoFilter;

public interface PontoRepositoryQuery {

	public List<Ponto> filtrar(PontoFilter pontoFilter);
}
