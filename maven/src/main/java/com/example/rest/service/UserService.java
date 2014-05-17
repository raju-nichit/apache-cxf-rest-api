package com.example.rest.service;

import com.example.rest.dto.UserDTO;

public interface UserService {
	public UserDTO getUserByAuthToken(String authToken);
}
