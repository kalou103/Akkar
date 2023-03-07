package com.example.akkar2.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsService {
    // Replace with your Twilio account SID
    private final static String ACCOUNT_SID = "AC0c322b9cef5473f69e18c0d8bdf226e3";
    // Replace with your Twilio auth token
    private final static String AUTH_TOKEN = "ed9eb1b6ee15e051a0f41df68bbf131a";
    // Replace with your Twilio phone number
    private final static String TWILIO_NUMBER = "15076974656";
    // Replace with your phone number
    private final static String ADMIN_NUMBER = "50403261";

    public static void sendSms(String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber(ADMIN_NUMBER), new PhoneNumber(TWILIO_NUMBER), message).create();
    }
}

