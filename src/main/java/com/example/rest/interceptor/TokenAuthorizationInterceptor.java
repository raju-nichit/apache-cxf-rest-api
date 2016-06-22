package com.example.rest.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.rest.dto.UserDTO;
import com.example.rest.model.ErrorModel;
import com.example.rest.model.ResponseModel;
import com.example.rest.service.UserService;
import com.example.rest.util.ResourceManager;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class TokenAuthorizationInterceptor extends
		AbstractPhaseInterceptor<Message> {
	private Logger logger = Logger
			.getLogger(TokenAuthorizationInterceptor.class);
	@Autowired
	private UserService userService;

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public TokenAuthorizationInterceptor() {
		super(Phase.PRE_INVOKE); // Put this interceptor in this phase
	}

	public void handleMessage(Message message) throws RuntimeException {
		HttpServletRequest httpRequest = (HttpServletRequest) message
				.get(AbstractHTTPDestination.HTTP_REQUEST);
		// get the authToken value from header
		String authToken = httpRequest.getHeader("authToken");
		logger.info("authToken=   " + authToken);
		UserDTO userDto = userService.getUserByAuthToken(authToken);
		if (userDto == null) {
//			logger.info(authToken
//					+ " "
//					+ ResourceManager
//							.getProperty("exception.message.invalid.user.token"));
//			
//			String errorMessage = ResourceManager
//					.getProperty("exception.message.invalid.user.token");
			ResponseModel responseModel = ResponseModel.getInstance();
			ErrorModel error = new ErrorModel(ResourceManager.getProperty("exception.message.invalid.user.token"));
			responseModel.setError(error);
			responseModel.setStatus("fail");
			String errorResponse = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				errorResponse = mapper.writeValueAsString(responseModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpServletResponse response = (HttpServletResponse) message
					.get(AbstractHTTPDestination.HTTP_RESPONSE);
			response.setStatus(500);
			try {
				response.getWriter().write(errorResponse);
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw new org.apache.cxf.interceptor.security.AccessDeniedException(
					ResourceManager
							.getProperty("exception.message.invalid.user.token"));

		}
		httpRequest.setAttribute("user", userDto);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
