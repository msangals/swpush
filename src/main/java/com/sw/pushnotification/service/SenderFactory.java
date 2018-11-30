package com.sw.pushnotification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sw.pushnotification.common.AbstractBuilder;
import com.sw.pushnotification.sender.ApnsSender;
import com.sw.pushnotification.sender.FcmSender;
import com.sw.pushnotification.sender.GcmSender;

import java.util.Objects;

public class SenderFactory {

    private GcmSender gcmSender;

    private FcmSender fcmSender;

    private ApnsSender apnsSender;

    private ObjectMapper objectMapper;

    private int maxRetries;

    private SenderFactory(GcmSender gcmSender, FcmSender fcmSender, ApnsSender apnsSender, ObjectMapper objectMapper, int maxRetries) {
        Objects.nonNull(objectMapper);
        this.objectMapper = objectMapper;
        this.maxRetries = maxRetries;
        this.gcmSender = gcmSender;
        this.fcmSender = fcmSender;
        this.apnsSender = apnsSender;
    }

    public GcmSender getGcmSender() {
        return gcmSender;
    }

    public FcmSender getFcmSender() {
        return fcmSender;
    }

    public ApnsSender getApnsSender() {
        return apnsSender;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public static class Builder extends AbstractBuilder<SenderFactory> {

        private GcmSender gcmSender;

        private FcmSender fcmSender;

        private ObjectMapper objectMapper;

        private ApnsSender apnsSender;

        private int maxRetries = 3;

        public SenderFactory.Builder withGcmSender(GcmSender gcmSender) {
            this.gcmSender = gcmSender;
            return this;
        }

        public SenderFactory.Builder withFcmSender(FcmSender fcmSender) {
            this.fcmSender = fcmSender;
            return this;
        }

        public SenderFactory.Builder withApnsSender(ApnsSender apnsSender) {
            this.apnsSender = apnsSender;
            return this;
        }

        public SenderFactory.Builder withObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public SenderFactory.Builder withMaxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        @Override
        public SenderFactory build() {
            return new SenderFactory(gcmSender, fcmSender, apnsSender, objectMapper, maxRetries);
        }
    }

}
