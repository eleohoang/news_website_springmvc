package spring_mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring_mvc.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity findOneByCode(String code);
	CategoryEntity findByCode(String code);
}
