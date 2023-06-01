package spring_mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring_mvc.converter.CategoryConverter;
import spring_mvc.converter.CommentConverter;
import spring_mvc.converter.NewConverter;
import spring_mvc.dto.CommentDTO;
import spring_mvc.dto.NewDTO;
import spring_mvc.entity.CategoryEntity;
import spring_mvc.entity.CommentEntity;
import spring_mvc.entity.NewEntity;
import spring_mvc.repository.CategoryRepository;
import spring_mvc.repository.CommentRepository;
import spring_mvc.repository.NewRepository;
import spring_mvc.service.INewsService;

@Service
public class NewsService implements INewsService {
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private NewConverter newConverter;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentConverter commentConverter;
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List <NewEntity> entities = newRepository.findAll(pageable).getContent();
		for (NewEntity item:entities) {
			NewDTO newDTO = newConverter.toDto(item);
			models.add(newDTO);	
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}

	@Override
	public NewDTO findById(Long id) {
		NewEntity newentity = newRepository.findOne(id);
		return newConverter.toDto(newentity);
	}
	
	@Override
	@Transactional
	public NewDTO save(NewDTO dto) {
		NewEntity newentity = new NewEntity();
		CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
		if (dto.getId() != null) {
			NewEntity oldNew = newRepository.findOne(dto.getId());
			oldNew.setCategory(category);
			newentity = newConverter.toEntity(oldNew, dto);
		} else {
			newentity = newConverter.toEntity(dto);
			newentity.setCategory(category);
		}
		return newConverter.toDto(newRepository.save(newentity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id: ids) {
			//delete comments in the new
			List<CommentEntity> entities = newRepository.getOne(id).getComments();
			for (CommentEntity entity: entities) {
				commentRepository.delete(entity.getId());
			}
			newRepository.delete(id);
		}
	}

	@Override
	public List<NewDTO> findNewList(long[] categoryIds) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = new ArrayList<>();
		
		for (long categoryId: categoryIds) {
			entities.clear();
			entities.add(newRepository.findLastNewByCategoryId(categoryId));
				for (NewEntity entitiy:entities) {
					if (entitiy != null) {
						NewDTO dto = newConverter.toDto(entitiy);
						dto.setCommentNumber(commentRepository.countNumberComment(dto.getId()));
						models.add(dto);	
				}
			}
				entities.clear();
		}
		return models;
	}

	@Override
	public NewDTO findTheNewest() {
		NewDTO dto = newConverter.toDto(newRepository.findFirst1ByOrderByCreatedDateDesc());
		dto.setCommentNumber(commentRepository.countNumberComment(dto.getId()));
		return dto;
	}

	@Override
	public NewDTO findPost(long id) {
		return newConverter.toDto(newRepository.findById(id));
	}

	@Override
	public List<NewDTO> findByCategoryCode(String categoryCode, long id) {
		List<NewDTO> models = new ArrayList<>();
		CategoryEntity category = categoryRepository.findByCode(categoryCode);
		for (NewEntity entitiy:category.getNews()) {
			if (entitiy.getId() != id) {
				NewDTO dto = newConverter.toDto(entitiy);
				dto.setCommentNumber(commentRepository.countNumberComment(dto.getId()));
				models.add(dto);	
			}
		}
		return models;
	}

	@Override
	public NewDTO findLastDetail(long categoryId) {
		NewDTO dto = newConverter.toDto(newRepository.findLastNewByCategoryId(categoryId));
		dto.setCommentNumber(commentRepository.countNumberComment(dto.getId()));
		return dto;
	}

}
