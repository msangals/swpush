package com.sw.pushnotification.service;

import com.sw.pushnotification.model.PushNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PushNotificationSender {

    private List<PushNotificationService> pushNotificationServices = new ArrayList<>();

    public PushNotificationSender(SenderFactory senderFactory) {
        pushNotificationServices.add(new GcmPushNotificationService(senderFactory));
        pushNotificationServices.add(new FcmPushNotificationService(senderFactory));
        pushNotificationServices.add(new ApnsPushNotificationService(senderFactory));
    }

    public void send(PushNotification pushNotification) {
        PushNotificationService pushNotificationService = findPushNotificationService(pushNotification.getServiceType());
        pushNotificationService.sendPush(pushNotification);
    }

    public void send(PushNotification pushNotification, String alertTitle, String alertBody) {
        PushNotificationService pushNotificationService = findPushNotificationService(pushNotification.getServiceType());
        pushNotificationService.sendPush(pushNotification, alertTitle, alertBody);
    }

    private PushNotificationService findPushNotificationService(PushNotification.ServiceType serviceType) {
        Optional<PushNotificationService> fetchedService = pushNotificationServices.stream()
                .filter(pushNotificationService -> pushNotificationService.isApplicable(serviceType))
                .findFirst();

        if (!fetchedService.isPresent()) {
            throw new IllegalArgumentException(String.format("Not sender service found for %s", serviceType.toString()));
        }

        return fetchedService.get();
    }

}
