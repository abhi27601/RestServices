package org.rahul.webdev.messenger.model;

import java.util.Date;

public class Comment {
	private long id;
	private String author;
	private String commentText;
	private Date created;
	
	public Comment() {
		
	}
	public Comment(long id, String text, String author) {
		this.id = id;
		this.commentText = text;
		this.author = author;
		this.created = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
