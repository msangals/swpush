package com.sw.pushnotification.service;

import com.sw.pushnotification.model.PushNotification;
import com.sw.pushnotification.sender.ApnsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ApnsPushNotificationService extends AbstractPushNotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApnsPushNotificationService.class);

    private static final PushNotification.ServiceType TYPE = PushNotification.ServiceType.APNS;

    public ApnsPushNotificationService(SenderFactory senderFactory) {
        super(senderFactory);
    }

    @Override
    public boolean isApplicable(PushNotification.ServiceType serviceType) {
        return TYPE.equals(serviceType);
    }

    @Override
    public void sendPush(PushNotification pushNotification) {
        sendPush(pushNotification, "", "");
    }

    @Override
    public void sendPush(PushNotification pushNotification, String alertTitle, String alertBody) {
        List<String> deviceIds = pushNotification.getDeviceIds();
        String payload = createPayload(pushNotification.getPayload());
        LOGGER.info("send push to ios devices: " + String.join(", ", deviceIds.toString()) + " with payload: " + payload);
        ApnsSender apnsSender = senderFactory.getApnsSender();
        apnsSender.send(pushNotification.getPayload(), alertTitle, alertBody, deviceIds);
    }
}
