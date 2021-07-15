package com.Yobulma.Yobulma.Twilio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {
    private  static  final String accountSid = "AC074f5caaf823a8407a1c40f1114e1f20" ;


    private  static  final String authToken= "070b65f2c247a54b78772b15a14b4f0d";
    private  static final  String trialNumber = "+12057404414";

    public TwilioConfiguration() {

    }


    public String getAccountSid() {
        return accountSid;
    }


    public String getAuthToken() {
        return authToken;
    }



    public String getTrialNumber() {
        return trialNumber;
    }


}
