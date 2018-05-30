package com.jefferson.pontoEletronicoapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
