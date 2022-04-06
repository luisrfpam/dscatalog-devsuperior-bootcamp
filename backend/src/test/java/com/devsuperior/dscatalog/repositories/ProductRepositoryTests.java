package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;

@DataJpaTest
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository repository;
	
	private long IdExisting;
	private long nonIdExisting;
	
	@BeforeEach
	void setUp() {
		// Arrange - instancie os objetos necessarios
		IdExisting = 20L;
		nonIdExisting = 99L;
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {		
		// Act - execute as ações necessarias
		repository.deleteById(IdExisting);	
		// Assert - declare o que deveria acontecer (resultado esperado)
		Optional<Product> objOptional = repository.findById(IdExisting);
		Assertions.assertFalse(objOptional.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonIdExisting);
		});
		
	}

}
