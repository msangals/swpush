package com.sw.pushnotification.sender;

import com.google.android.gcm.server.Endpoint;
import com.google.android.gcm.server.Sender;

public class FcmSender extends Sender {

    public FcmSender(String key) {
        this(key, Endpoint.FCM);
    }

    private FcmSender(String key, Endpoint endpoint) {
        super(key, endpoint);
    }
}
