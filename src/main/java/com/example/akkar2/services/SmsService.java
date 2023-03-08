package com.example.akkar2.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsService {
    // Replace with your Twilio account SID
    private final static String ACCOUNT_SID = "ACeac24de359c784c7d648f27168747abf";
    // Replace with your Twilio auth token
    private final static String AUTH_TOKEN = "75014aab453c0d3b188e93da23e6943b";
    // Replace with your Twilio phone number
    private final static String TWILIO_NUMBER = "15076088386";
    // Replace with your phone number
    private final static String ADMIN_NUMBER = "+21625222542";

    public static void sendSms(String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber(ADMIN_NUMBER), new PhoneNumber(TWILIO_NUMBER), message).create();
    }
}

