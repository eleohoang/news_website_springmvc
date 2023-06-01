package spring_mvc.converter;

import org.springframework.stereotype.Component;

import spring_mvc.dto.CommentDTO;
import spring_mvc.entity.CommentEntity;

@Component
public class CommentConverter {
	public CommentDTO toDto(CommentEntity entity) {
		CommentDTO result = new CommentDTO();
		result.setId(entity.getId());
		result.setContent(entity.getContent());
		result.setNewId(entity.getNewEntity().getId());
		result.setUserId(entity.getUserEntity().getId());
		result.setCreatedBy(entity.getCreatedBy());
		result.setCreatedDate(entity.getCreatedDate());
		return result;
	}
	
	public CommentEntity toEntity(CommentDTO dto) {
		CommentEntity result = new CommentEntity();
		result.setContent(dto.getContent());
		return result;
	}
	
	public CommentEntity toEntity(CommentEntity result, CommentDTO dto) {
		if (dto.getContent() != null && dto.getContent() != result.getContent()) {
			result.setContent(dto.getContent());
		}
		return result;
	}
}
