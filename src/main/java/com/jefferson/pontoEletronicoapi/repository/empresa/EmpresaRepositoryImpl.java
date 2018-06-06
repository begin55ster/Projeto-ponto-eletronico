package com.jefferson.pontoEletronicoapi.repository.empresa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jefferson.pontoEletronicoapi.model.Empresa;
import com.jefferson.pontoEletronicoapi.repository.filter.EmpresaFilter;


public class EmpresaRepositoryImpl implements EmpresaRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Empresa> filtrar(EmpresaFilter empresaFilter, Pageable pageable) {
		//Criteria JPA a do Hibernate depreciou
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteria = builder.createQuery(Empresa.class);
		
		//Filtros da criteria
		Root<Empresa> root = criteria.from(Empresa.class);
		
		//criar restrições
		Predicate[] predicates = criarRestricoes(empresaFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Empresa> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(empresaFilter)) ;
	}

	private Predicate[] criarRestricoes(EmpresaFilter empresaFilter, CriteriaBuilder builder, Root<Empresa> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		filtrarCampoNome(empresaFilter, builder, root, predicates);
		filtrarCampoCnpj(empresaFilter, builder, root, predicates);
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void filtrarCampoCnpj(EmpresaFilter empresaFilter, CriteriaBuilder builder, Root<Empresa> root,
			List<Predicate> predicates) {
		if(!StringUtils.isEmpty(empresaFilter.getCnpj())) {
			predicates.add(builder.like(
					builder.lower(root.get(Empresa.Fields.CNPJ.toString())), "%" + empresaFilter.getCnpj().toLowerCase() + "%"));
		}
	}

	private void filtrarCampoNome(EmpresaFilter empresaFilter, CriteriaBuilder builder, Root<Empresa> root,
			List<Predicate> predicates) {
		if(!StringUtils.isEmpty(empresaFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Empresa.Fields.NOME.toString())), "%" + empresaFilter.getNome().toLowerCase() + "%"));
		}
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Empresa> query, Pageable pageable) {
	
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(EmpresaFilter empresaFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Empresa> root = criteria.from(Empresa.class);
		Predicate[] predicates = criarRestricoes(empresaFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
