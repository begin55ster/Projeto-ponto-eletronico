package com.jefferson.pontoEletronicoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jefferson.pontoEletronicoapi.model.Ponto;
import com.jefferson.pontoEletronicoapi.repository.ponto.PontoRepositoryQuery;

public interface PontoRepository extends JpaRepository<Ponto, Long>, PontoRepositoryQuery{
	
	

}
