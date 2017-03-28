package com.bsejawal.jaxrs.rest;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/date/{dateString}")
public class DateResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Date getRequestedDate(@PathParam("dateString") MyDate myDate){
		return Calendar.getInstance().getTime();
//		return "Got "+myDate.toString();
		
		
	}
	
	
	

}
