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
import com.jefferson.pontoEletronicoapi.model.Ponto;
import com.jefferson.pontoEletronicoapi.repository.PontoRepository;

@RestController
@RequestMapping("/pontos")
public class PontoResource {
	
	@Autowired
	private PontoRepository pontoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Ponto> listarTodos() {
		return pontoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Ponto> criarPonto(@Valid @RequestBody Ponto ponto, HttpServletResponse response) {
		Ponto pontoSalvo = pontoRepository.save(ponto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pontoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pontoSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ponto> buscarPontoPeloId(@PathVariable Long id) {
		Ponto ponto = pontoRepository.findOne(id);
		return ponto != null ? ResponseEntity.ok(ponto) : ResponseEntity.notFound().build();
	}

}
