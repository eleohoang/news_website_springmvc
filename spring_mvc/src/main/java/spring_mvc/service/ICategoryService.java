package spring_mvc.service;

import java.util.List;
import java.util.Map;

import spring_mvc.dto.CategoryDTO;

public interface ICategoryService {
	Map<String, String> findAll();
	List <CategoryDTO> findAllList();
}
