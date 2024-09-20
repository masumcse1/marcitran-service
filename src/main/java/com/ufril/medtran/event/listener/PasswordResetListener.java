package com.ufril.medtran.event.listener;

import com.ufril.medtran.event.OnPasswordResetEvent;
import com.ufril.medtran.persistence.domain.account.PasswordResetToken;
import com.ufril.medtran.persistence.service.UserService;
import com.ufril.medtran.service.MailSenderService;
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
public class PasswordResetListener implements ApplicationListener<OnPasswordResetEvent> {

    private static Logger logger = Logger.getLogger(PasswordResetListener.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public void onApplicationEvent(OnPasswordResetEvent event) {
        this.respondPasswordResetRequest(event);
    }

    private void respondPasswordResetRequest(final OnPasswordResetEvent event) {
        final String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = userService.createPasswordResetToken(event.getUser(), token);
        logger.debug("before call the sendMail call");
        mailSenderService.sendMail(event, passwordResetToken.getToken());
//        System.out.println("Selim-Reza Password Resent");
//        mailSenderService.sendMail(event, passwordResetToken.getToken());
    }

}
