package spring_mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_mvc.constant.SystemConstant;
import spring_mvc.converter.UserConverter;
import spring_mvc.dto.UserDTO;
import spring_mvc.repository.UserRepository;
import spring_mvc.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private UserConverter userConverter;
	
	@Override
	public UserDTO findUser(String userName) {
		return userConverter.toDto(userRepository.findOneByUserNameAndStatus(userName, SystemConstant.ACTIVE_STATUS));
	}
}
