package com.Yobulma.Yobulma.Twilio;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceTwilio {
    private  final  SmsSender smsSender;

    public ServiceTwilio(SmsSender smsSender) {
        this.smsSender = smsSender;
    }



    @Autowired

    public  void  sendSms(SmsRequest smsRequest){
    smsSender.sendSms(smsRequest);
    }
}
