package spring_mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring_mvc.converter.CommentConverter;
import spring_mvc.converter.UserConverter;
import spring_mvc.dto.CommentDTO;
import spring_mvc.dto.UserDTO;
import spring_mvc.entity.CommentEntity;
import spring_mvc.entity.NewEntity;
import spring_mvc.entity.UserEntity;
import spring_mvc.repository.CommentRepository;
import spring_mvc.repository.NewRepository;
import spring_mvc.repository.UserRepository;
import spring_mvc.service.ICommentService;

@Service
public class CommentService implements ICommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentConverter commentConverter;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public CommentDTO save(CommentDTO dto) {
		CommentEntity commentEntity = new CommentEntity();
		NewEntity newEntity = newRepository.findById(dto.getNewId());
		UserEntity userEntity = userRepository.findOneById(dto.getUserId());
		if (dto.getId() != null) {
			CommentEntity oldComment = commentRepository.findOne(dto.getId());
			oldComment.setNewEntity(newEntity);
			oldComment.setUserEntity(userEntity);
			commentEntity = commentConverter.toEntity(oldComment, dto);
		} else {
			commentEntity = commentConverter.toEntity(dto);
			commentEntity.setNewEntity(newEntity);
			commentEntity.setUserEntity(userEntity);
		}
		return commentConverter.toDto(commentRepository.save(commentEntity));
	}

	@Override
	public List<CommentDTO> getComments(Long newId) {
		List<CommentDTO> models = new ArrayList<>();
		List<CommentEntity> entities = newRepository.getOne(newId).getComments();
		for (CommentEntity item:entities) {
			CommentDTO dto = commentConverter.toDto(item);
			
			UserDTO user = userConverter.toDto(userRepository.findOneById(dto.getUserId()));
			dto.setUserName(user.getUserName());
			
			models.add(dto);	
		}
		return models;
	}

}
