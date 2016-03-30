package org.rahul.webdev.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.rahul.webdev.messenger.database.DatabaseClass;
import org.rahul.webdev.messenger.exception.DataNotFoundException;
import org.rahul.webdev.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1L, "I am Rahul", "Rahul"));
		messages.put(2L, new Message(2L, "I am Rahul again", "Rahul"));
		messages.put(3L, new Message(3L, "I am Rohan", "Rohan"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
		/*Message m1 = new Message(1L, "I am Rahul", "Rahul");
		Message m2 = new Message(2L, "I am Rahul again", "Rahul");
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		list.add(m3);
		return list;*/
		
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> paginatedList = new ArrayList<Message>(messages.values());
		if(start+size>paginatedList.size()) {
			return new ArrayList<>();
		}
		return paginatedList.subList(start, start+size);
	}
	
	public Message getMessage(long id) {
		Message message =  messages.get(id);
		if(message==null) {
			throw new DataNotFoundException("Message "+id+" not found.");
		}
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId()<=0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
