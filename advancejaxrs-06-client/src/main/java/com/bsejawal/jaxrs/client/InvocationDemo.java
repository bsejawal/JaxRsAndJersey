package com.bsejawal.jaxrs.client;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class InvocationDemo {

	public static void main(String[] args) {

		InvocationDemo demo = new InvocationDemo();
		
		Invocation invocation = demo.prepareRequestForMessageByYear(2017);
		Response response = invocation.invoke();
//		Message message = response.readEntity(Message.class);
		System.out.println(response.getStatus());
		
	}

	private Invocation prepareRequestForMessageByYear(int year) {
		Client client = ClientBuilder.newClient();
		 return  client.target("http://localhost:8080/advancejaxrs-06-client/webapi/")
		.path("messages")
		.queryParam("year", year)
		.request(MediaType.APPLICATION_JSON)
		.buildGet();
		
		
	}

}
