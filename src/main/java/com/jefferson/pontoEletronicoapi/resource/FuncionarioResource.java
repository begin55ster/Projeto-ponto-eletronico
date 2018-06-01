package com.jefferson.pontoEletronicoapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jefferson.pontoEletronicoapi.event.RecursoCriadoEvent;
import com.jefferson.pontoEletronicoapi.model.Funcionario;
import com.jefferson.pontoEletronicoapi.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Funcionario> listarTodos() {
		return funcionarioRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> criarFuncionario(@Valid @RequestBody Funcionario funcionario, HttpServletResponse response) {
		Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, funcionarioSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscarFuncionarioPeloId(@PathVariable Long id) {
		Funcionario funcionario = funcionarioRepository.findOne(id);
		return funcionario != null ? ResponseEntity.ok(funcionario) : ResponseEntity.notFound().build();
	}

}
