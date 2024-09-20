package com.ufril.medtran.dto.fcm;

import com.ufril.medtran.enumeration.DeviceOSType;

public class FCMDeviceTokenDTO {

	private DeviceOSType deviceOSType;
	private String deviceToken;

	public DeviceOSType getDeviceOSType() {
		return deviceOSType;
	}

	public void setDeviceOSType(DeviceOSType deviceOSType) {
		this.deviceOSType = deviceOSType;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
}
