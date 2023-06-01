package spring_mvc.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring_mvc.dto.NewDTO;
import spring_mvc.service.INewsService;

@RestController(value="newAPIOfAdmin")
public class NewAPI {
	
	@Autowired
	private INewsService newService;
	
	@PostMapping("/api/new")
	public NewDTO createNew(@RequestBody  NewDTO newDTO) {
		return newService.save(newDTO);
	}
	
	@PutMapping("/api/new")
	public NewDTO updateNew(@RequestBody NewDTO updateNew) {
		return newService.save(updateNew);
	}
	
	@DeleteMapping("/api/new")
	public void deleteNew(@RequestBody long[] ids) {
		newService.delete(ids);
	}
}
