package spring_mvc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring_mvc.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long> {
	 @Query(value = "SELECT n.* FROM new n WHERE n.category_id=:categoryId ORDER BY n.createddate DESC limit 1", nativeQuery = true)
	 NewEntity findLastNewByCategoryId(@Param("categoryId") Long categoryId);
	 
	 NewEntity findFirst1ByOrderByCreatedDateDesc();
	 NewEntity findById(Long id);
}
