package com.devsuperior.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.entities.Category;

// Controlador REST que vai responder as requisições deste recurso
@RestController // transforma esse recurso em REST
@RequestMapping(value = "/categories") // rota REST do seu recurso - a rota
public class CategoryResource {
	
	// Criação de um Endpoint - a primeira rota possível que irá responder a requisição
	// ResponseEntity é um objeto do spring que ele encapsula uma resposta HTTP 
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		
		List<Category> list = new ArrayList<Category>();
		list.add(new Category(1L, "Books"));
		list.add(new Category(2L, "Eletronics"));
		return ResponseEntity.ok().body(list);
		
	}

}
