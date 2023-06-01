package spring_mvc.service;

import java.util.List;

import spring_mvc.dto.CommentDTO;

public interface ICommentService {
	CommentDTO save(CommentDTO dto);
	List<CommentDTO> getComments(Long newId);
}
