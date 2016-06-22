package com.example.rest.model;

import java.io.Serializable;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ErrorModel implements Serializable {
	private static final long serialVersionUID = -271581896020647350L;
	private Response.Status status;
	private String message;

	public Response.Status getStatus() {
		return status;
	}

	public void setStatus(Response.Status status) {
		this.status = status;
	}

	public ErrorModel(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorModel(String message, Status status) {
		super();
		this.status = status;
		this.message = message;
	}

	public ErrorModel() {
		super();
	}
}
