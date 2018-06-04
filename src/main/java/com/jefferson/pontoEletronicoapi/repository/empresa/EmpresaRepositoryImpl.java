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

import com.jefferson.pontoEletronicoapi.model.Empresa;
import com.jefferson.pontoEletronicoapi.repository.filter.EmpresaFilter;


public class EmpresaRepositoryImpl implements EmpresaRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Empresa> filtrar(EmpresaFilter empresaFilter) {
		//Criteria JPA a do Hibernate depreciou
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteria = builder.createQuery(Empresa.class);
		
		//Filtros da criteria
		Root<Empresa> root = criteria.from(Empresa.class);
		
		//criar restrições
		Predicate[] predicates = criarRestricoes(empresaFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Empresa> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(EmpresaFilter empresaFilter, CriteriaBuilder builder, Root<Empresa> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(empresaFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Empresa.Fields.NOME.toString())), "%" + empresaFilter.getNome().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(empresaFilter.getCnpj())) {
			predicates.add(builder.like(
					builder.lower(root.get(Empresa.Fields.CNPJ.toString())), "%" + empresaFilter.getCnpj().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
