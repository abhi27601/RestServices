package org.rahul.webdev.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
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

import org.rahul.webdev.messenger.model.Message;
import org.rahul.webdev.messenger.resources.bean.MessageFilterBean;
import org.rahul.webdev.messenger.service.MessageService;

//Resource makes a call to the service.
@Path("/messages")
@Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
		if(messageFilterBean.getYear()>0) {
			return messageService.getAllMessagesForYear(messageFilterBean.getYear());
		}
		if(messageFilterBean.getStart()>0&&messageFilterBean.getSize()>0) {
			return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@POST
	public Response addMessages(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
		
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") long messageId) {
		return messageService.removeMessage(messageId);
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		message.addLink(uriInfo.getAbsolutePath().toString(), "self");
		message.addLink(uriInfo.getBaseUriBuilder()
						.path(MessageResource.class)
						.path(MessageResource.class, "getComments")
						.path(CommentResource.class)
						.resolveTemplate("messageId", message.getId())
						.build().toString(), "comments");
		return message;
	}
	
	
	//No GET because it can be post a comment or retrieve a comment
	@Path("/{messageId}/comments")
	public CommentResource getComments() {
		CommentResource commentResource  =new CommentResource();
		return commentResource;
	}
}
