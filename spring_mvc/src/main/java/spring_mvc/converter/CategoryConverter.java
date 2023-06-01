package spring_mvc.converter;

import org.springframework.stereotype.Component;

import spring_mvc.dto.CategoryDTO;
import spring_mvc.entity.CategoryEntity;

@Component
public class CategoryConverter {
	public CategoryDTO toDto(CategoryEntity entity) {
		CategoryDTO result = new CategoryDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setCode(entity.getCode());
		return result;
	}
	
	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity result = new CategoryEntity();
		result.setName(dto.getName());
		result.setCode(dto.getCode());
		return result;
	}
}
