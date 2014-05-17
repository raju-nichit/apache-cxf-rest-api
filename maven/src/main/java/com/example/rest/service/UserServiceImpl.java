package com.example.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.rest.dao.UserDao;
import com.example.rest.dto.UserDTO;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDTO getUserByAuthToken(String authToken){
		UserDTO dto=null;
		List<UserDTO> userDtos = userDao.getUserByAuthToken(authToken);
		if(!userDtos.isEmpty()){
			dto=(UserDTO) userDtos.get(0);
			return dto;
		}
		dto=new UserDTO();
		dto.setName("Harcoded username");
		dto.setAuthToken(authToken);
		return dto;
	}
}
