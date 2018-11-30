package com.sw.pushnotification.service;

import com.google.android.gcm.server.Message;
import com.sw.pushnotification.model.PushNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FcmPushNotificationService extends AbstractPushNotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcmPushNotificationService.class);

    private static final PushNotification.ServiceType TYPE = PushNotification.ServiceType.FCM;

    public FcmPushNotificationService(SenderFactory senderFactory) {
        super(senderFactory);
    }

    @Override
    public boolean isApplicable(PushNotification.ServiceType serviceType) {
        return TYPE.equals(serviceType);
    }

    @Override
    public void sendPush(PushNotification pushNotification) {
        Message.Builder builder = new Message.Builder();
        builder.addData(PAYLOAD_KEY, createPayload(pushNotification.getPayload()));
        try {
            senderFactory.getFcmSender().send(builder.build(), pushNotification.getDeviceIds(), senderFactory.getMaxRetries());
        } catch (IOException e) {
            LOGGER.error("Fail sending push notification", e);
        }
    }

    @Override
    public void sendPush(PushNotification pushNotification, String alertTitle, String alertBody) {
        sendPush(pushNotification);
    }
}
