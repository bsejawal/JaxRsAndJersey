package com.bsejawal.jaxrs.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.bsejawal.jaxrs.messenger.models.Message;

public class GenericDemo {

	public static void main(String args[]) {
		Client client = ClientBuilder.newClient();
		List<Message> messages = client.target("http://localhost:8080/advancejaxrs-06-client/webapi/")
				.path("messages")
				.queryParam("year", 2017)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Message>>() {});
		
		for (Message m : messages) {
			System.out.println(m);
		}
		// System.out.println(messages);
	}

}
