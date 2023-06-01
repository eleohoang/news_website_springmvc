package spring_mvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_mvc.converter.CategoryConverter;
import spring_mvc.dto.CategoryDTO;
import spring_mvc.entity.CategoryEntity;
import spring_mvc.repository.CategoryRepository;
import spring_mvc.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	@Autowired	
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryConverter categoryConverter;
	
	@Override
	public Map<String, String> findAll() {
		Map<String, String> results = new HashMap<>();
		List<CategoryEntity> entities = categoryRepository.findAll();
		for (CategoryEntity item:entities) {
			results.put(item.getCode(), item.getName());
		}
		return results;
	}

	@Override
	public List<CategoryDTO> findAllList() {
		List<CategoryDTO> results = new ArrayList<>();
		List<CategoryEntity> entities = categoryRepository.findAll();
		for (CategoryEntity item:entities) {
			results.add(categoryConverter.toDto(item));
		}
		return results;
	}
}
