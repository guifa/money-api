package com.guifa.money.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.guifa.money.api.model.Category;
import com.guifa.money.api.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	@PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_VIEWER')) and #oauth2.hasScope('read')")
	public ResponseEntity<List<Category>> list() {
		List<Category> categories = categoryService.findAll();
		
		return ResponseEntity.ok(categories);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category savedCategory = categoryService.save(category, response);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_VIEWER')) and #oauth2.hasScope('read')")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category category = categoryService.findById(id);
		
		return ResponseEntity.ok(category);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public void delete(@PathVariable Long id) {
		categoryService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<Category> update(@PathVariable Long id, @Valid @RequestBody Category category) {
		Category categoryToUpdate = categoryService.update(category, id);
		
		return ResponseEntity.ok(categoryToUpdate);
	}

}
