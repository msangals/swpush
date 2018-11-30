package com.sw.pushnotification.model;

import java.util.ArrayList;
import java.util.List;

public class PushNotification {

    public enum ServiceType {
        GCM,
        FCM,
        APNS
    }

    private ServiceType serviceType;
    private Object payload;
    private List<String> deviceIds = new ArrayList<String>();

    public PushNotification(ServiceType serviceType, Object payload, List<String> deviceIds) {
        this.serviceType = serviceType;
        this.payload = payload;
        this.deviceIds = deviceIds;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public Object getPayload() {
        return payload;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }
}