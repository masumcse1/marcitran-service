package com.ufril.medtran.util;

import com.ufril.medtran.exception.ResourceNotFoundException;
import com.ufril.medtran.persistence.domain.account.User;
import com.ufril.medtran.persistence.service.UserService;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Created by moin on 12/15/15.
 */
public class ResourceUtils {

    public static void userNotExist(UserService userService, MessageSource messageSource, String userID, Locale locale) {
        User user = userService.getUserByUserNameOrEmail(userID);
        if (user == null) {
            throw new ResourceNotFoundException(messageSource.getMessage("message.userNotExist", null, locale) + userID);
        }
    }
}
