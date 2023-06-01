package spring_mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring_mvc.entity.NewEntity;
import spring_mvc.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUserNameAndStatus(String name, int status);
	UserEntity findOneById(Long id);
}
