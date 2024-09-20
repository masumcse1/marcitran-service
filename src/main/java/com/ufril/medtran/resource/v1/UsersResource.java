package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.account.*;
import com.ufril.medtran.dto.common.Message;
import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.exception.BadRequestException;
import com.ufril.medtran.helper.MessageHelper;
import com.ufril.medtran.helper.ResourceValidationHelper;
import com.ufril.medtran.persistence.domain.account.Role;
import com.ufril.medtran.persistence.domain.account.User;
import com.ufril.medtran.persistence.domain.account.VerificationToken;
import com.ufril.medtran.persistence.service.UserService;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@RestController(value = "usersResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "users", description = "User API")
public class UsersResource {
    private static Logger logger = Logger.getLogger(UsersResource.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ResourceValidationHelper validationHelper;
    @Autowired
    private MessageHelper messageHelper;

    @RequestMapping(value = "/users/login", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO, Locale locale) {
        logger.debug("Inside Login resource method");
        User user = validationHelper.isUserFound(loginDTO.getUserId(), locale);
        GetProfileDTO profile;
        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            profile = userService.getProfile(loginDTO.getUserId());
        } else {
            throw new BadRequestException(messageHelper.getMessage("message.invalidCredentials", locale));
        }
        return new ResponseEntity<>(new Response(StatusType.OK, profile), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserProfile/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserProfile(@PathVariable("email") final String email, Locale locale) {
        validationHelper.isUserFound(email, locale);
        GetProfileDTO profile = userService.getProfile(email);
        return new ResponseEntity<>(new Response(StatusType.OK, profile), HttpStatus.OK);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO userData, final Locale locale) {
        logger.debug("Inside createUser");
        validationHelper.isEmailAlreadyUsed(userData.getEmail(), locale);

        User newUser = userService.createUser(userData, null);

        //eventPublisher.publishEvent(new OnSignupCompleteEvent(newUser, locale));

        Message message = messageHelper.buildMessage201("message.accountCreated", locale);
        return new ResponseEntity<>(new Response(StatusType.OK, message), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/change-password", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> changePassword(@RequestBody HashMap map, Locale locale) {
        validationHelper.isUserFound(map.get("email").toString(), locale);

        String password = map.get("password").toString();
        String matchingPassword = map.get("matchingPassword").toString();
        if (password.equals(matchingPassword)) {
            userService.changePassword(map.get("email").toString(), password);
        } else {
            return new ResponseEntity<>(new Response(StatusType.OK, "Password doesn't match"), HttpStatus.OK);
        }

        Message message = messageHelper.buildMessage200("message.changePassword", locale);
        return new ResponseEntity<>(new Response(StatusType.OK, message), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllUsers() {
        List<GetProfileDTO> allUsers = userService.getAllUSers();
        return new ResponseEntity<>(new Response(StatusType.OK, allUsers), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllRoles", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getAllRoles() {
        List<RoleDTO> allRoles = userService.findAllRoles();
        return new ResponseEntity<>(new Response(StatusType.OK, allRoles), HttpStatus.OK);
    }

    @RequestMapping(value = "/isUserExistByEmail", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> isUserExistByEmail(@RequestBody HashMap<String, Object> map) {
        if (map.containsKey("email")) {
            boolean value = userService.isUserEmailExists(map.get("email").toString());
            return new ResponseEntity<>(new Response(StatusType.OK, value), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response(StatusType.OK, false), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/confirm-signup/{token}", method = RequestMethod.GET)
    public ResponseEntity<?> confirmCreateUser(@PathVariable("token") final String token, final Locale locale) {
        final VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            throw new BadRequestException(messageHelper.getMessage("auth.message.invalidToken", locale));
        }
        /* final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new BadRequestException(messageHelper.getMessage("auth.message.expired", locale));
        }*/
        final User verificationTokenUser = verificationToken.getUser();
        verificationTokenUser.setStatus("ACTIVE");
        verificationTokenUser.setLastUpdatedOn(new Date());
        userService.saveUser(verificationTokenUser);
        return new ResponseEntity<>(new Response(StatusType.OK, "Your Hajj App account is now verified"), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateUserProfile(@Valid @RequestBody UpdateProfileDTO profile, Locale locale) {
        validationHelper.isUserFound(profile.getEmail(), locale);

        User user = userService.getUserByEmail(profile.getEmail());

        User updatedUser = userService.updateProfile(user.getUsername(), profile, null);
        Message message = messageHelper.buildMessage200("message.profileUpdated", locale);
        return new ResponseEntity<>(new Response(StatusType.OK, message), HttpStatus.OK);
    }

    //
//	@RequestMapping(
//			value = "/users/resend-signup-token",
//			method = RequestMethod.POST,
//			consumes = {MediaType.APPLICATION_JSON_VALUE},
//			produces = {MediaType.APPLICATION_JSON_VALUE}
//	)
//	@ApiOperation(
//			value = "Resend Account Verification Email",
//			response = Response.class,
//			notes = "If a user don't get the account verification email which contains a url to verify his account, then he can request to resend the email again to his email."
//	)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "", response = Response.class),
//			@ApiResponse(code = 404, message = "Unable to find User Profile", response = Response.class)
//	})
//	public ResponseEntity<?> resendSignupToken(@Valid @RequestBody ResendTokenDTO resendTokenDTO, final Locale locale) {
//		// TODO not here, if verification token is expired and the user is disabled then the system should remove the user entry from db
//		User user = validationHelper.isUserFound(resendTokenDTO.getUserId(), locale);
//		eventPublisher.publishEvent(new OnSignupCompleteEvent(user, locale));
//		Message message = messageHelper.buildMessage200("message.resendTokenEmail", locale);
//		return new ResponseEntity<>(new Response(StatusType.OK, message), HttpStatus.OK);
//	}
//
//	@RequestMapping(
//			value = "/users/reset-password",
//			method = RequestMethod.POST,
//			consumes = {MediaType.APPLICATION_JSON_VALUE},
//			produces = {MediaType.APPLICATION_JSON_VALUE}
//	)
//	@ApiOperation(
//			value = "Send Reset Password Email",
//			response = Response.class,
//			notes = "To reset password you have to provide your account email address. an email will sent which contains an url or a token by using this you can reset your password")
//	@ApiResponses(value = {
//			@ApiResponse(code = 201, message = "Reset Password Request Successful", response = Response.class),
//			@ApiResponse(code = 500, message = "Error sending request", response = Response.class)
//	})
//	public ResponseEntity<?> resetPasswordRequest(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO, Locale locale) {
//		User user = validationHelper.isUserFound(resetPasswordDTO.getUserId(), locale);
//		logger.debug("OnPasswordResetEvent");
//		eventPublisher.publishEvent(new OnPasswordResetEvent(user, locale));
//		Message message = messageHelper.buildMessage200("message.resetPassword", locale);
//		return new ResponseEntity<>(new Response(StatusType.OK, message), HttpStatus.OK);
//	}
//
//	@RequestMapping(
//			value = "/users/change-password",
//			method = RequestMethod.POST,
//			consumes = {MediaType.APPLICATION_JSON_VALUE},
//			produces = {MediaType.APPLICATION_JSON_VALUE}
//	)
//	@ApiOperation(
//			value = "Change Password",
//			response = Response.class,
//			notes = "Change Password")
//	@ApiResponses(value = {
//			@ApiResponse(code = 201, message = "Password Changed Successful", response = Response.class),
//			@ApiResponse(code = 500, message = "Error changing password", response = Response.class)
//	})
//	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO, Locale locale) {
//		PasswordResetToken resetToken = userService.getPasswordResetToken(changePasswordDTO.getToken());
//		User user = userService.getUserByUserNameOrEmail(changePasswordDTO.getUserId());
//		if ((resetToken == null) || !resetToken.getUser().getUsername().equals(user.getUsername())) {
//			throw new BadRequestException(messageHelper.getMessage("auth.message.prInvalidToken", locale));
//		}
//		Calendar cal = Calendar.getInstance();
//		if (DateUtils.isExpired(resetToken.getExpiryDate())) {
//			throw new BadRequestException(messageHelper.getMessage("auth.message.prExpired", locale));
//		}
//		userService.changePassword(changePasswordDTO);
//		Message message = messageHelper.buildMessage200("message.changePassword", locale);
//		return new ResponseEntity<>(new Response(StatusType.OK, message), HttpStatus.OK);
//	}
}
