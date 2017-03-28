package com.bsejawal.jaxrs.rest;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/shortdate")
public class CustomMediaTypeResource {
	
	@GET
	@Produces(value = {MediaType.TEXT_PLAIN, "text/shortDate"}) //Accept text/shortDate
	public Date getRequestedDate(){
		return Calendar.getInstance().getTime();
		
		
	}
	
	
	

}
