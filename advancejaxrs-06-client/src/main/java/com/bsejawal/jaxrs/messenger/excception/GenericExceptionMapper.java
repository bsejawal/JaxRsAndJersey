package com.bsejawal.jaxrs.messenger.excception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.bsejawal.jaxrs.messenger.models.ErrorMessage;

//@Provider // uncomment this annotation if you want to use this genericException mapper
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable t) {
		ErrorMessage errorMessage = new ErrorMessage(t.getMessage(), 500, "http://www.vesh.com.np");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}



}
