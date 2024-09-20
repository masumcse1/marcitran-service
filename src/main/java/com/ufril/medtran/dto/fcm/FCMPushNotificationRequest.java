package com.ufril.medtran.dto.fcm;

public class FCMPushNotificationRequest {

	private String title;
	private String message;
	private String topic;
	private String token;

	public FCMPushNotificationRequest() {
	}

	public FCMPushNotificationRequest(String title, String messageBody, String topicName) {
		this.title = title;
		this.message = messageBody;
		this.topic = topicName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
