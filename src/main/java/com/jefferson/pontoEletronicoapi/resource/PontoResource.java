package com.jefferson.pontoEletronicoapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jefferson.pontoEletronicoapi.model.Ponto;
import com.jefferson.pontoEletronicoapi.repository.PontoRepository;

@RestController
@RequestMapping("/pontos")
public class PontoResource {
	
	@Autowired
	private PontoRepository pontoRepository;
	
	@GetMapping
	public List<Ponto> listarTodos() {
		return pontoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Ponto> criarPonto(@Valid @RequestBody Ponto ponto, HttpServletResponse response) {
		Ponto pontoSalvo = pontoRepository.save(ponto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(pontoSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(pontoSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ponto> buscarPontoPeloId(@PathVariable Long id) {
		Ponto ponto = pontoRepository.findOne(id);
		return ponto != null ? ResponseEntity.ok(ponto) : ResponseEntity.notFound().build();
	}

}
