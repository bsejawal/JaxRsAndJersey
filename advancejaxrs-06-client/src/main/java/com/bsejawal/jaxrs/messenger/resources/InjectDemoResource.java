package com.bsejawal.jaxrs.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectdemo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String param, 
			@HeaderParam("customHeaderValue") String header,
			@CookieParam("name") String cookie){
		return "Matrix Param: "+ param +", Header param :"+header+", Cookie param : "+cookie;
	}
	
	@GET
	@Path("context")
	public String getValueUsingContext(@Context UriInfo uriInfo, 
			@Context HttpHeaders headers){
		
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		return "path : "+path +", cookie:"+cookies;
	}

}
