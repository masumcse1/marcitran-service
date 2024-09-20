package com.ufril.medtran.event;

import com.ufril.medtran.persistence.domain.account.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by moin on 11/2/15.
 */
public class OnPasswordResetEvent extends ApplicationEvent {

    private final Locale locale;
    private final User user;


    public OnPasswordResetEvent(final User user, final Locale locale) {
        super(user);
        this.user = user;
        this.locale = locale;
    }

    public User getUser() {
        return user;
    }

    public Locale getLocale() {
        return locale;
    }

}
