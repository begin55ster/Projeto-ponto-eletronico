package com.jefferson.pontoEletronicoapi.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jefferson.pontoEletronicoapi.event.RecursoCriadoEvent;
import com.jefferson.pontoEletronicoapi.model.Funcionario;
import com.jefferson.pontoEletronicoapi.repository.FuncionarioRepository;
import com.jefferson.pontoEletronicoapi.repository.filter.FuncionarioFilter;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Funcionario> listarTodos(FuncionarioFilter funcionarioFilter, Pageable pageable) {
		return funcionarioRepository.filtrar(funcionarioFilter, pageable);
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
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerFuncionario(@PathVariable Long id) {
		funcionarioRepository.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
		Funcionario funcionarioSalvo = funcionarioRepository.findOne(id);
		BeanUtils.copyProperties(funcionario, funcionarioSalvo, "id");
		funcionarioRepository.save(funcionarioSalvo);
        return ResponseEntity.ok(funcionarioSalvo);
	}

}
