package com.example.rest.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseModel {

	private ErrorModel error;
	private Object object;
	private String status;
	private String message;

	public ErrorModel getError() {
		return error;
	}

	public void setError(ErrorModel error) {
		this.error = error;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public static ResponseModel getInstance() {
		ResponseModel response=new ResponseModel();
		response.setStatus("SUCCESS");
		return response;
	}
}
