package com.sw.pushnotification.sender;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;
import com.notnoop.apns.PayloadBuilder;

import java.io.InputStream;
import java.util.List;

public class ApnsSender {

    private ApnsService apnsService;

    public ApnsSender(InputStream cert, String certPass, boolean isSandbox) {
        ApnsServiceBuilder apnsServiceBuilder = APNS.newService();
        apnsServiceBuilder.withCert(cert, certPass);

        if (isSandbox) {
            apnsServiceBuilder.withSandboxDestination();
        } else {
            apnsServiceBuilder.withProductionDestination();
        }

        apnsService = apnsServiceBuilder.build();
    }

    public void send(Object payload, String alertTitle, String alertBody, List<String> deviceTokens) {
        PayloadBuilder payloadBuilder = APNS.newPayload();
        payloadBuilder.alertTitle(alertTitle);
        payloadBuilder.alertBody(alertBody);
        payloadBuilder.sound("default");
        payloadBuilder.customField("PushNotification", payload);
        apnsService.push(deviceTokens, payloadBuilder.build());
    }
}
