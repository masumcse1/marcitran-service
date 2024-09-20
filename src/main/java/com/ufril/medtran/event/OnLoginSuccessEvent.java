package com.ufril.medtran.event;

import com.ufril.medtran.persistence.domain.account.User;
import org.springframework.context.ApplicationEvent;

/**
 * @author moin
 */
public class OnLoginSuccessEvent extends ApplicationEvent {

    private final User user;

    /**
     * Create a new ApplicationEvent.
     *
     * @param user the component that published the event (never {@code null})
     */
    public OnLoginSuccessEvent(final User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
