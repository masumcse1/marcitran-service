package com.ufril.medtran.event.listener;

import com.ufril.medtran.event.OnSignupCompleteEvent;
import com.ufril.medtran.persistence.domain.account.VerificationToken;
import com.ufril.medtran.persistence.service.UserService;
import com.ufril.medtran.service.MailSenderService;
import com.ufril.medtran.util.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author moin
 *
 */
@Component
public class SignupListener implements ApplicationListener<OnSignupCompleteEvent> {

    private static Logger logger = Logger.getLogger(SignupListener.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public void onApplicationEvent(OnSignupCompleteEvent onSignupCompleteEvent) {
        this.confirmSignup(onSignupCompleteEvent);
    }

    private void confirmSignup(final OnSignupCompleteEvent event) {
        final String token = Utils.getAlphaNumeric(8);;
        VerificationToken verificationToken = userService.createVerificationToken(event.getUser(), token);
        logger.debug("Inside confirmSignup on SignupListener. Before sendMail Call");
//        mailSenderService.sendMail(event, verificationToken.getToken());
    }
}
