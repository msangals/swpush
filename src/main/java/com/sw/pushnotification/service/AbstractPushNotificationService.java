package com.sw.pushnotification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPushNotificationService implements PushNotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPushNotificationService.class);

    protected static final String PAYLOAD_KEY = "payload";

    protected SenderFactory senderFactory;

    public AbstractPushNotificationService(SenderFactory senderFactory) {
        this.senderFactory = senderFactory;
    }

    protected String createPayload(Object object) {
        try {
            return senderFactory.getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed converting payload to json", e);
            throw new IllegalArgumentException("payload can not be converted to json");
        }
    }

}
