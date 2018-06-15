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
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.jefferson.pontoEletronicoapi.model.Ponto;
import com.jefferson.pontoEletronicoapi.repository.PontoRepository;
import com.jefferson.pontoEletronicoapi.repository.filter.PontoFilter;

@RestController
@RequestMapping("/pontos")
public class PontoResource {
	
	@Autowired
	private PontoRepository pontoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PONTO') and #oauth2.hasScope('read')")
	public Page<Ponto> listarTodos(PontoFilter pontoFilter, Pageable pageable) {
		return pontoRepository.filtrar(pontoFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PONTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Ponto> criarPonto(@Valid @RequestBody Ponto ponto, HttpServletResponse response) {
		Ponto pontoSalvo = pontoRepository.save(ponto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pontoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pontoSalvo);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PONTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Ponto> buscarPontoPeloId(@PathVariable Long id) {
		Ponto ponto = pontoRepository.findOne(id);
		return ponto != null ? ResponseEntity.ok(ponto) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PONTO') and #oauth2.hasScope('read')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerPonto(@PathVariable Long id) {
		pontoRepository.delete(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PONTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Ponto> atualizarPonto(@PathVariable Long id, @Valid @RequestBody Ponto ponto) {
		Ponto pontoSalvo = pontoRepository.findOne(id);
		BeanUtils.copyProperties(ponto, pontoSalvo, "id");
		pontoRepository.save(pontoSalvo);
        return ResponseEntity.ok(pontoSalvo);
	}

}
