package com.jefferson.pontoEletronicoapi.repository.funcionario;

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

import com.jefferson.pontoEletronicoapi.model.Empresa;
import com.jefferson.pontoEletronicoapi.model.Funcionario;
import com.jefferson.pontoEletronicoapi.repository.filter.FuncionarioFilter;

public class FuncionarioRepositoryImpl implements FuncionarioRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	

	@Override
	public Page<Funcionario> filtrar(FuncionarioFilter funcionarioFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Funcionario> criteria = builder.createQuery(Funcionario.class);
		Root<Funcionario> root = criteria.from(Funcionario.class);
		
		Predicate[] predicates = criarRestricoes(funcionarioFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Funcionario> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(funcionarioFilter)) ;
	}

	private Predicate[] criarRestricoes(FuncionarioFilter funcionarioFilter, CriteriaBuilder builder,
			Root<Funcionario> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		filtrarCampoNome(funcionarioFilter, builder, root, predicates);
		filtrarCampoCpf(funcionarioFilter, builder, root, predicates);
		filtrarCampoEmpresa(funcionarioFilter, builder, root, predicates);
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void filtrarCampoEmpresa(FuncionarioFilter funcionarioFilter, CriteriaBuilder builder,
			Root<Funcionario> root, List<Predicate> predicates) {
		
		if(!StringUtils.isEmpty(funcionarioFilter.getNomeEmpresa())) {
			//join para fazer a pesquisa
			Join<Funcionario, Empresa> empresa = root.join(Funcionario.Fields.EMPRESA.toString());
			predicates.add(builder.like(
					builder.lower(empresa.get(Empresa.Fields.NOME.toString())), "%" + funcionarioFilter.getNomeEmpresa().toLowerCase() + "%"));
		}
	}

	private void filtrarCampoCpf(FuncionarioFilter funcionarioFilter, CriteriaBuilder builder, Root<Funcionario> root,
			List<Predicate> predicates) {
	
		if(!StringUtils.isEmpty(funcionarioFilter.getCpf())) {		
			predicates.add(builder.like(
					builder.lower(root.get(Funcionario.Fields.CPF.toString())), "%" + funcionarioFilter.getCpf().toLowerCase() + "%"));
		}
	}

	private void filtrarCampoNome(FuncionarioFilter funcionarioFilter, CriteriaBuilder builder, Root<Funcionario> root,
			List<Predicate> predicates) {
		
		if(!StringUtils.isEmpty(funcionarioFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Funcionario.Fields.NOME.toString())), "%" + funcionarioFilter.getNome().toLowerCase() + "%"));
		}
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Funcionario> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(FuncionarioFilter funcionarioFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Funcionario> root = criteria.from(Funcionario.class);
		Predicate[] predicates = criarRestricoes(funcionarioFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
