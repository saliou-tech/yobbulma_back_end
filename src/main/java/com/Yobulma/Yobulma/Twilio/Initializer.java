package com.Yobulma.Yobulma.Twilio;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Initializer {
    private  final  TwilioConfiguration twilioConfiguration;
    private  final  static Logger LOGGER = LoggerFactory.getLogger(Initializer.class);
    @Autowired
    public Initializer(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        System.out.println(twilioConfiguration.getAccountSid());
        Twilio.init(twilioConfiguration.getAccountSid(),twilioConfiguration.getAuthToken());


        LOGGER.info("Twilio initialiwed .... with account sid {}",twilioConfiguration.getAccountSid());
    }
}
