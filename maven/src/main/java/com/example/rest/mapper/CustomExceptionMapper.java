package com.example.rest.mapper;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.example.rest.model.ErrorModel;
import com.example.rest.model.ResponseModel;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CustomExceptionMapper implements ExceptionMapper<Exception> {
	Logger logger = Logger.getLogger(CustomExceptionMapper.class);

	@Context
	private HttpHeaders headers;

	public Response toResponse(Exception e) {
		ResponseModel response = ResponseModel.getInstance();
		ErrorModel error = new ErrorModel(e.getMessage());
		response.setError(error);
		response.setStatus("FAIL");
		logger.error(e.getMessage(), e);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(response).type(headers.getMediaType()).build();
	}

}