package com.jefferson.pontoEletronicoapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jefferson.pontoEletronicoapi.model.Empresa;
import com.jefferson.pontoEletronicoapi.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@GetMapping
	public List<Empresa> listarTodos() {
		return empresaRepository.findAll();
	}
	
}
