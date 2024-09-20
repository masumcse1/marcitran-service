package com.ufril.medtran.helper;

import com.ufril.medtran.exception.BadRequestException;
import com.ufril.medtran.exception.ResourceNotFoundException;
import com.ufril.medtran.persistence.domain.account.User;
import com.ufril.medtran.persistence.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author moin
 *
 */
@Component
public class ResourceValidationHelper {
    private static final Logger logger = Logger.getLogger(ResourceValidationHelper.class);

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserService userService;


    /**
     * If a user found with the userID then it will return the user object otherwise throw ResourceNotFoundException
     * @param userID
     * @param locale
     * @return
     */
    public User isUserFound(String userID, Locale locale) {
        User user = userService.getUserByUserNameOrEmail(userID);
        if (user == null) {
            throw new ResourceNotFoundException(messageSource.getMessage("message.userIDNotFound", new Object[] {userID}, locale));
        } else {
            return user;
        }
    }

    /**
     * If the username is already used then a BadRequestException will thrown
     * @param username
     * @param locale
     */
    public void isUsernameAlreadyUsed(String username, Locale locale) {
        if (userService.isUserExists(username)) {
            throw new BadRequestException(messageSource.getMessage("message.alreadyUsed", new Object[] {username}, locale));
        }
    }

    /**
     * If the email is already used then a BadRequestException will thrown
     * @param email
     * @param locale
     */
    public void isEmailAlreadyUsed(String email, Locale locale) {
        if (userService.isUserEmailExists(email)) {
            throw new BadRequestException(messageSource.getMessage("message.alreadyUsed", new Object[] {email}, locale));
        }
    }

}
