package com.bsejawal.jaxrs.messenger.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.bsejawal.jaxrs.messenger.database.DatabaseClass;
import com.bsejawal.jaxrs.messenger.excception.DataNotFoundException;
import com.bsejawal.jaxrs.messenger.models.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1L, "Hello World!!", "Bhesh"));
		messages.put(2L, new Message(2L, "Hello Jersey!!", "Bhesh"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year)
				messageForYear.add(message);
		}
		return messageForYear;	
	}
	
	public List<Message> getAllMessagePaginated(int start, int size){
		List<Message> list = new ArrayList<>(messages.values());
		if(start+size> list.size()) return new ArrayList<>();
		return list.subList(start, start+size);
	}

	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message== null){
			throw new DataNotFoundException("Message with id "+ id +" not found");			
		}
		return messages.get(id);
		
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
