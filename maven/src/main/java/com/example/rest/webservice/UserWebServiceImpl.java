package com.example.rest.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.rest.dto.UserDTO;
import com.example.rest.service.UserService;

@Component
public class UserWebServiceImpl implements UserWebService {

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GET
	@Path("/user")
	public UserDTO getUser(@QueryParam("token") String authToken) {
		UserDTO userDto = userService.getUserByAuthToken(authToken);
		return userDto;

	}

}
