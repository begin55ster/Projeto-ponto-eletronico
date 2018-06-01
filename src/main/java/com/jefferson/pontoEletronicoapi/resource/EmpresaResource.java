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
import com.jefferson.pontoEletronicoapi.model.Empresa;
import com.jefferson.pontoEletronicoapi.repository.EmpresaRepository;


@RestController
@RequestMapping("/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Empresa> listarTodos() {
		return empresaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Empresa> criarEmpresa(@Valid @RequestBody Empresa empresa, HttpServletResponse response) {
		Empresa empresaSalva = empresaRepository.save(empresa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, empresaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empresa> buscarEmpresaPorId(@PathVariable Long id) {
		Empresa empresa = empresaRepository.findOne(id);
		return empresa != null ? ResponseEntity.ok(empresa) : ResponseEntity.notFound().build();
	}
	
}
