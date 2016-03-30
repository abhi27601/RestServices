package org.rahul.webdev.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.rahul.webdev.messenger.model.Message;
import org.rahul.webdev.messenger.model.Profile;

public class DatabaseClass {
	public static Map<Long, Message> messages = new HashMap<>();
	public static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
}
