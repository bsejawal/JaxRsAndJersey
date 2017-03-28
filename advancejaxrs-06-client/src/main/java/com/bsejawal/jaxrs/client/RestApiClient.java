package com.bsejawal.jaxrs.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bsejawal.jaxrs.messenger.models.Message;

public class RestApiClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();

		/*
		 * WebTarget target = client.target(
		 * "http://localhost:8080/advancejaxrs-06-client/webapi/messages/2");
		 * Builder builder = target.request(); Response response =
		 * builder.get(); Message message = response.readEntity(Message.class);
		 */

		/*
		 * Message message = client.target(
		 * "http://localhost:8080/advancejaxrs-06-client/webapi/messages/2")
		 * .request(MediaType.APPLICATION_JSON) // set content type here
		 * .get(Message.class);
		 */

		// String message =
		// client.target("http://localhost:8080/advancejaxrs-06-client/webapi/messages/2")
		// .request(MediaType.APPLICATION_JSON) // set content type here
		// .get(String.class);

		// Response response =
		// client.target("http://localhost:8080/advancejaxrs-06-client/webapi/messages/2")
		// .request(MediaType.APPLICATION_JSON) // set content type here
		// .get();//
		// Message message = response.readEntity(Message.class);

		WebTarget target = client.target("http://localhost:8080/advancejaxrs-06-client/webapi");
		WebTarget messageTarget = target.path("messages");
		WebTarget singleMessageTarget = messageTarget.path("{messageId}");

		Message message1 = singleMessageTarget
				.resolveTemplate("messageId", "1")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		
		System.out.println(message1);
		Message message2 = singleMessageTarget
				.resolveTemplate("messageId", "2")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		System.out.println(message2);
		
		
		Message newMessage = new Message(3, "My new Message from jaxrs client", "bsejawal");
		Response postResponse = messageTarget
		.request()
		.post(Entity.json(newMessage));
		if(postResponse.getStatus() != 201)
			System.out.println("Error!!");
		
		Message createdMessage = postResponse.readEntity(Message.class);
		
		System.out.println(createdMessage);
		
	}

}
