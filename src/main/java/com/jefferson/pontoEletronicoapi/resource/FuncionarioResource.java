package com.jefferson.pontoEletronicoapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jefferson.pontoEletronicoapi.model.Funcionario;
import com.jefferson.pontoEletronicoapi.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public List<Funcionario> listarTodos() {
		return funcionarioRepository.findAll();
	}

}
