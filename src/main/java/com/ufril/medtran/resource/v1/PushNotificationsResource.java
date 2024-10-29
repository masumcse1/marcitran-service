package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.fcm.FCMPushNotificationRequest;
import com.ufril.medtran.dto.fcm.PushNotificationResponse;
import com.ufril.medtran.service.PushNotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moin
 */
@RestController(value = "pushNotificationResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "push notifications", description = " FCMPush Notification API")
public class PushNotificationsResource {

    @Autowired
    private PushNotificationService pushNotificationService;

	@ApiOperation(
			value = "Send FCM Push notification to topic",
			response = PushNotificationResponse.class
	)
	@RequestMapping(value = "/fcm/notification/topic", method = RequestMethod.POST)
	public ResponseEntity<?> sendNotification(@RequestBody FCMPushNotificationRequest request) {
		pushNotificationService.sendPushNotificationWithoutData(request);
		return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
	}

	@RequestMapping(value = "/fcm/notification/token", method = RequestMethod.POST)
	public ResponseEntity<?> sendTokenNotification(@RequestBody FCMPushNotificationRequest request) {
		pushNotificationService.sendPushNotificationToToken(request);
		return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
	}


	@RequestMapping(value = "/fcm/notification/data", method = RequestMethod.POST)
	public ResponseEntity<?> sendDataNotification(@RequestBody FCMPushNotificationRequest request) {
		pushNotificationService.sendPushNotification(request);
		return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
	}

}
