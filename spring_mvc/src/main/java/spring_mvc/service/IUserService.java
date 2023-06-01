package spring_mvc.service;

import spring_mvc.dto.UserDTO;

public interface IUserService {
	UserDTO findUser(String userName);
}
