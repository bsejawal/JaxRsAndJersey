package com.bsejawal.jaxrs.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.bsejawal.jaxrs.messenger.models.Message;
import com.bsejawal.jaxrs.messenger.resources.beans.MessageFilterBean;
import com.bsejawal.jaxrs.messenger.services.MessageService;

@Path("messages") // RequestMapping
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {
	MessageService messageService = new MessageService();

	
	// @Produces(MediaType.APPLICATION_XML)//@Produces(MediaType.TEXT_PLAIN) //
	@GET // method
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0){
			System.out.println("get year");
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() > 0 && filterBean.getSize() > 0){
			System.out.println("get start ");
			return messageService.getAllMessagePaginated(filterBean.getStart()+1, filterBean.getSize()+1);
		}
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	// @Produces(MediaType.APPLICATION_XML)
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message =  messageService.getMessage(id);
		
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");
		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder() //http://localhost:8080/messenger/webapi/
				.path(MessageResource.class) //messages
				.path(MessageResource.class, "getCommentResource") // second parameter is name of the method that has comment uri-> 2
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString(); //http://localhost:8080/messenger/webapi/profiles/Bhesh
				return uri;
				
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder() //http://localhost:8080/messenger/webapi/
				.path(ProfileResource.class) //profiles
				.path(message.getAuthor()) //{autherName}
				.build()
				.toString(); //http://localhost:8080/messenger/webapi/profiles/Bhesh
				return uri;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder() //http://localhost:8080/messenger/webapi/
		.path(MessageResource.class) //messages
		.path(Long.toString(message.getId())) //1
		.build()
		.toString(); //http://localhost:8080/messenger/webapi/messages/1
		return uri;
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
//		return Response.status(Status.CREATED)
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
		.entity(newMessage)
		.build();
		
//		return messageService.addMessage(message);
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		System.out.println("delete message");
		messageService.removeMessage(id);
	}
	
	
	@Path("{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "test";
	}

}
