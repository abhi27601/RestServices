package org.rahul.webdev.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.rahul.webdev.messenger.database.DatabaseClass;
import org.rahul.webdev.messenger.model.Comment;
import org.rahul.webdev.messenger.model.Message;

public class CommentService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments= messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId) {
		return messages.get(messageId).getComments().get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		return messages.get(messageId).getComments().remove(commentId);
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId()<=0) {
			return null;
		}
		else  {
			comments.put(comment.getId(), comment);
			return comment;
		}
	}
}
