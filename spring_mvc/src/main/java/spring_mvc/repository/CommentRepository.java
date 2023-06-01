package spring_mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring_mvc.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
	@Query(value = "SELECT count(id) FROM comment WHERE new_id=:newId", nativeQuery = true)
	Long countNumberComment(@Param("newId") Long newId);

}
