package spring_mvc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import spring_mvc.dto.CategoryDTO;
import spring_mvc.dto.NewDTO;

public interface INewsService {
	List<NewDTO> findAll(Pageable pageable);
	int getTotalItem();
	NewDTO findById(Long id);
	NewDTO save(NewDTO dto);
	void delete(long[] ids);
	List<NewDTO> findNewList(long[] categoryIds);
	NewDTO findTheNewest();
	NewDTO findPost(long id);
	List<NewDTO> findByCategoryCode(String categoryCode, long id);
	NewDTO findLastDetail(long categoryId);
}
