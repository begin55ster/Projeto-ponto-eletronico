package com.jefferson.pontoEletronicoapi.repository.ponto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jefferson.pontoEletronicoapi.model.Funcionario;
import com.jefferson.pontoEletronicoapi.model.Ponto;
import com.jefferson.pontoEletronicoapi.repository.filter.PontoFilter;

public class PontoRepositoryImpl implements PontoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Ponto> filtrar(PontoFilter pontoFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Ponto> criteria = builder.createQuery(Ponto.class);
		Root<Ponto> root = criteria.from(Ponto.class);
		
		Predicate[] predicates = criarRestricoes(pontoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Ponto> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(pontoFilter)) ;
	}

	private Predicate[] criarRestricoes(PontoFilter pontoFilter, CriteriaBuilder builder, Root<Ponto> root) {
		
		List<Predicate> predicates = filtrarCampoFuncionario(pontoFilter, builder, root);
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private List<Predicate> filtrarCampoFuncionario(PontoFilter pontoFilter, CriteriaBuilder builder, Root<Ponto> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(pontoFilter.getNomeFuncionario())) {
			Join<Ponto, Funcionario> funcionarioJoin = root.join(Ponto.Field.FUNCIONARIO.toString());
			predicates.add(builder.like(
					builder.lower(funcionarioJoin.get(Funcionario.Fields.NOME.toString())), "%" + pontoFilter.getNomeFuncionario().toLowerCase()  + "%"));
		}
		return predicates;
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Ponto> query, Pageable pageable) {
	
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(PontoFilter pontoFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Ponto> root = criteria.from(Ponto.class);
		Predicate[] predicates = criarRestricoes(pontoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
