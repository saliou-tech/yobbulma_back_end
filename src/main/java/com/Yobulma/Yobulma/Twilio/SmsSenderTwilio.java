package com.Yobulma.Yobulma.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class SmsSenderTwilio implements SmsSender {

    private  final  TwilioConfiguration twilioConfiguration;
    private  final  static Logger LOGGER = LoggerFactory.getLogger(Initializer.class);

    @Autowired
    public SmsSenderTwilio(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {

            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to,from,message);
            creator.create();
            LOGGER.info("Send Sms {}",smsRequest);





    }

    private boolean isPhoneNumberValid(String phoneNumber) {
    // chercher un regex pour les numeros pour les numeros de telephone

    return  true;
    }
}
