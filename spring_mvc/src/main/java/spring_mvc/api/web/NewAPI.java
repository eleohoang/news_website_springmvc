package spring_mvc.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring_mvc.dto.CommentDTO;
import spring_mvc.service.ICommentService;
import spring_mvc.service.impl.CommentService;

@RestController(value="newAPIOfWeb")
public class NewAPI {
	
	@Autowired ICommentService commentService;
	
	@PostMapping("/api/comment")
	public CommentDTO createNew(@RequestBody CommentDTO commentDTO) {
		return commentService.save(commentDTO);
	}
	
	@PutMapping("/api/comment")
	public CommentDTO updateNew(@RequestBody CommentDTO updateNew) {
		return commentService.save(updateNew);
	}
}
