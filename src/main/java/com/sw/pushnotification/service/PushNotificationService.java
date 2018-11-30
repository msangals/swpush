package com.sw.pushnotification.service;


import com.sw.pushnotification.model.PushNotification;

public interface PushNotificationService {

    boolean isApplicable(PushNotification.ServiceType serviceType);

    void sendPush(PushNotification pushNotification);

    /**
     * Apple has its own PushNotification structure. Use this method to send apple push supporting the alert title
     * and alert body function.
     */
    void sendPush(PushNotification pushNotification, String alertTitle, String alertBody);

}
