package spring_mvc.converter;

import org.springframework.stereotype.Component;

import spring_mvc.dto.UserDTO;
import spring_mvc.entity.UserEntity;

@Component
public class UserConverter {
	public UserDTO toDto(UserEntity entity) {
		UserDTO result = new UserDTO();
		result.setId(entity.getId());
		result.setUserName(entity.getUserName());
		return result;
	}
	
	
}
