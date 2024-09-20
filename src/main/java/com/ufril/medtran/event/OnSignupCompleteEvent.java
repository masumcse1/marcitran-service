package com.ufril.medtran.event;

import com.ufril.medtran.persistence.domain.account.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by moin on 10/21/15.
 */
public class OnSignupCompleteEvent extends ApplicationEvent {

    private final Locale locale;
    private final User user;


    public OnSignupCompleteEvent(final User user, final Locale locale) {
        super(user);
        this.user = user;
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser() {
        return user;
    }

}
