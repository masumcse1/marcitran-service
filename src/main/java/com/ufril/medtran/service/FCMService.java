package com.ufril.medtran.service;

import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ufril.medtran.dto.fcm.FCMPushNotificationRequest;
import com.ufril.medtran.enumeration.FCMNotificationParameter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {

	private static Logger logger= Logger.getLogger(FCMService.class);

	public void sendMessage(Map<String, String> data, FCMPushNotificationRequest request)
			throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageWithData(data, request);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		String response = sendAndGetResponse(message);
		logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response+ " msg "+jsonOutput);
	}

	public void sendMessageWithoutData(FCMPushNotificationRequest request)
			throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageWithoutData(request);
		String response = sendAndGetResponse(message);
		logger.info("Sent message without data. Topic: " + request.getTopic() + ", " + response);
	}

	private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
		return FirebaseMessaging.getInstance().sendAsync(message).get();
	}

	public void sendMessageToToken(FCMPushNotificationRequest request)
			throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageToToken(request);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		String response = sendAndGetResponse(message);
		logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response+ " msg "+jsonOutput);
	}

	private AndroidConfig getAndroidConfig(String topic) {
		return AndroidConfig.builder()
				.setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
				.setPriority(AndroidConfig.Priority.HIGH)
				.setNotification(AndroidNotification.builder().setSound(FCMNotificationParameter.SOUND.getValue())
						.setColor(FCMNotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
	}

	private ApnsConfig getApnsConfig(String topic) {
		return ApnsConfig.builder()
				.setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
	}

	private Message getPreconfiguredMessageToToken(FCMPushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
				.build();
	}

	private Message getPreconfiguredMessageWithoutData(FCMPushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
				.build();
	}

	private Message getPreconfiguredMessageWithData(Map<String, String> data, FCMPushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).putAllData(data).setToken(request.getToken())
				.build();
	}

	private Message.Builder getPreconfiguredMessageBuilder(FCMPushNotificationRequest request) {
		AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
		ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
		return Message.builder()
				.setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
						new Notification(request.getTitle(), request.getMessage()));
	}


}
