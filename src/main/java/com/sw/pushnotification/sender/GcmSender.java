package com.sw.pushnotification.sender;

import com.google.android.gcm.server.Endpoint;
import com.google.android.gcm.server.Sender;

public class GcmSender extends Sender {

    public GcmSender(String key) {
        this(key, Endpoint.GCM);
    }

    private GcmSender(String key, Endpoint endpoint) {
        super(key, endpoint);
    }
}
