package com.devsuperior.dscatalog.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.dtos.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;

// Controlador REST que vai responder as requisições deste recurso
@RestController // transforma esse recurso em REST
@RequestMapping(value = "/categories") // rota REST do seu recurso - a rota
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	// Criação de um Endpoint - a primeira rota possível que irá responder a requisição
	// ResponseEntity é um objeto do spring que ele encapsula uma resposta HTTP
	/*
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {		
		List<CategoryDTO> list = service.findAll();
		//List<Category> list = new ArrayList<Category>();
		//list.add(new Category(1L, "Books"));
		//list.add(new Category(2L, "Eletronics"));
		return ResponseEntity.ok().body(list);		
	}*/
	
	@GetMapping
	public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable) {
		
		Page<CategoryDTO> list = service.findAllPaged(pageable);
		
		return ResponseEntity.ok().body(list);		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {		
		CategoryDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);		
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
		dto = service.update(id, dto);		
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}

}
