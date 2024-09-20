package com.ufril.medtran.service;


import com.ufril.medtran.event.OnPasswordResetEvent;
import com.ufril.medtran.event.OnSignupCompleteEvent;
import com.ufril.medtran.persistence.domain.account.User;
import com.ufril.medtran.persistence.domain.dispatch.PCRLog;

public interface MailSenderService {

    /**
     * This method is for sending an email during the user signup process to
     * verify the user email validity.
     * @param event
     * @param token
     */
    void sendMail(OnSignupCompleteEvent event, String token);

    void sendMail(OnPasswordResetEvent event, String token);

    void sendPcrReport(PCRLog pcrLog, String email);

}
