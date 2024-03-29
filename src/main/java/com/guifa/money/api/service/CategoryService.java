package com.guifa.money.api.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.guifa.money.api.event.CreatedResourceEvent;
import com.guifa.money.api.model.Category;
import com.guifa.money.api.repository.category.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category save(Category category, HttpServletResponse response) {
		Category savedCategory = categoryRepository.save(category);

		applicationEventPublisher.publishEvent(new CreatedResourceEvent(this, response, savedCategory.getId()));
		
		return savedCategory;
	}

	public Category findById(Long id) {
		Category category;
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		
		category = optionalCategory.orElseThrow(() -> new EmptyResultDataAccessException("No class " + Category.class.getName() +  " entity with id " + id +  " exists!", 1));
		
		return category;
	}

	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	public Category update(Category category, Long id) {
		Category categoryToUpdate = findById(id);
		
		BeanUtils.copyProperties(category, categoryToUpdate, "id");
		
		return categoryRepository.save(categoryToUpdate);
	}

}
