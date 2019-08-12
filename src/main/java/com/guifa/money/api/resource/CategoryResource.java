package com.guifa.money.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guifa.money.api.event.CreatedResourceEvent;
import com.guifa.money.api.model.Category;
import com.guifa.money.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@GetMapping
	public ResponseEntity<List<Category>> list() {
		List<Category> categories = categoryRepository.findAll();
		
		return ResponseEntity.ok(categories);
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category savedCategory = categoryRepository.save(category);
		
		applicationEventPublisher.publishEvent(new CreatedResourceEvent(this, response, savedCategory.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Category>> findById(@PathVariable Long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		
		return optionalCategory.isPresent() ? ResponseEntity.ok(optionalCategory) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		categoryRepository.deleteById(id);
	}

}
