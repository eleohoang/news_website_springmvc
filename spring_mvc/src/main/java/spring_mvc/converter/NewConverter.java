package spring_mvc.converter;

import org.springframework.stereotype.Component;

import spring_mvc.dto.NewDTO;
import spring_mvc.entity.NewEntity;

@Component
public class NewConverter {
	public NewDTO toDto(NewEntity entity) {
		NewDTO result = new NewDTO();
		result.setId(entity.getId());
		result.setTitle(entity.getTitle());
		result.setShortDescription(entity.getShortDescription());
		result.setContent(entity.getContent());
		result.setThumbnail(entity.getThumbnail());
		result.setCategoryCode(entity.getCategory().getCode());
		result.setCategoryId(entity.getCategory().getId());
		result.setCreatedBy(entity.getCreatedBy());
		result.setCreatedDate(entity.getCreatedDate());
		return result;
	}
	
	public NewEntity toEntity(NewDTO dto) {
		NewEntity result = new NewEntity();
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());
		result.setThumbnail(dto.getThumbnail());
		return result;
	}
	
	public NewEntity toEntity(NewEntity result, NewDTO dto) {
		if (dto.getTitle() != null && dto.getTitle() != result.getTitle()) {
			result.setTitle(dto.getTitle());
		}
		if (dto.getShortDescription() != null && dto.getShortDescription() != result.getShortDescription()) {
			result.setShortDescription(dto.getShortDescription());
		}
		if (dto.getContent() != null && dto.getContent() != result.getContent()) {
			result.setContent(dto.getContent());
		}
		if (dto.getThumbnail() != null && dto.getThumbnail() != result.getThumbnail()) {
			result.setThumbnail(dto.getThumbnail());
		}
		
		return result;
	}
}
