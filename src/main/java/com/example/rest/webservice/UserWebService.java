package com.example.rest.webservice;

import com.example.rest.dto.UserDTO;

public interface UserWebService {
	UserDTO getUser(String authToken);
}
