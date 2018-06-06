package com.jefferson.pontoEletronicoapi.repository.ponto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jefferson.pontoEletronicoapi.model.Ponto;
import com.jefferson.pontoEletronicoapi.repository.filter.PontoFilter;

public interface PontoRepositoryQuery {

	public Page<Ponto> filtrar(PontoFilter pontoFilter, Pageable pageable);
}
