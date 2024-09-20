package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.account.*;
import com.ufril.medtran.enumeration.RoleType;
import com.ufril.medtran.persistence.domain.account.PasswordResetToken;
import com.ufril.medtran.persistence.domain.account.Role;
import com.ufril.medtran.persistence.domain.account.User;
import com.ufril.medtran.persistence.domain.account.VerificationToken;
import com.ufril.medtran.persistence.domain.common.Address;

import java.util.List;

/**
 * @author moin
 *
 */
public interface UserService {

	User getUserByUserID(Integer userID);

    User createUser(CreateUserDTO userData, Address address);

    GetProfileDTO getProfile(String userID);

    User getUser(String verificationToken);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User getUserByEmployeeID(Integer employeeId);

    /**
     * It takes userID which can be username or email and return an user object if exist
     * @param userID
     * @return
     */
    User getUserByUserNameOrEmail(String userID);

    User updateProfile(String username, UpdateProfileDTO profile, Address address);

    void saveUser(User user);

    void changePassword(ChangePasswordDTO changePasswordDTO);

	void changePassword(String userEmail, String password);

    boolean isUserExists(String username);

    boolean isUserEmailExists(String email);

    /**
     * Create a verification token for a newly registered user, so that he can verify his account using it.
     * @param user
     * @param token
     */
    VerificationToken createVerificationToken(User user, String token);

    /**
     * Get the verification token so that the system can match the token with the
     * requested url token and enable the user account.
     * @param token
     * @return
     */
    VerificationToken getVerificationToken(String token);

    /**
     * Generate a new verification token on request of the user. May be the existing
     * token expired so the user need another new token to enable his account.
     * @param existingToken
     * @return
     */
    VerificationToken generateNewVerificationToken(String existingToken);

    /**
     * Create a token to use in password request
     * @param user
     * @param token
     */
    PasswordResetToken createPasswordResetToken(User user, String token);

    /**
     * Get a PasswordResetToken object by using the token string
     * @param token
     * @return
     */
    PasswordResetToken getPasswordResetToken(String token);

    /**
     * Get user object by using the token string
     * @param token
     * @return
     */
    User getUserByPasswordResetToken(String token);

    User getUserByVerificationToken(String token);

    Role getRoleByName(RoleType roleType);

    User removeRoleFromUser(User user, RoleType roleType);

    User assignRoleToUser(User user, RoleType roleType);

	public List<GetProfileDTO> getAllUSers();

	List<RoleDTO> findAllRoles();

}
