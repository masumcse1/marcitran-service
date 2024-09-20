package com.ufril.medtran.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author moin
 *
 */
public class RestAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = -6648681959852806984L;

    public RestAuthenticationException(String msg) {
        super(msg);
    }

    public RestAuthenticationException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
