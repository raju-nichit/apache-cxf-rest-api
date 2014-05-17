package com.example.rest.dao;

import java.util.List;

import com.example.rest.dto.UserDTO;

public interface UserDao {

	public List<UserDTO> getUserByAuthToken(String authToken);

}
