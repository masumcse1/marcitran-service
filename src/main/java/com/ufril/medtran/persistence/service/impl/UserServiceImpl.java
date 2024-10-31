package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.dto.account.*;
import com.ufril.medtran.enumeration.RoleType;
import com.ufril.medtran.persistence.domain.account.*;
import com.ufril.medtran.persistence.domain.common.Address;
import com.ufril.medtran.persistence.repository.account.*;
import com.ufril.medtran.persistence.service.UserService;
import com.ufril.medtran.util.DateUtils;
import com.ufril.medtran.util.MapperUtils;
import com.ufril.medtran.util.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author moin
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public User getUserByUserID(Integer userID) {
        return userRepository.findOne(userID);
    }

    @Override
    public User createUser(CreateUserDTO userDTO, Address address) {
        Company company = companyRepository.findOne(userDTO.getCompanyId());

        List<Role> roles = new ArrayList<>();
        roles.add(getRoleByName(userDTO.getRole()));

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setStatus(userDTO.getStatus());
        user.setEmployeeId(userDTO.getEmployeeId());
        user.setTwoFAEnabled(userDTO.isTwoFAEnabled());
        user.setRoles(roles);
        user.setLocked(false);
        user.setCompany(company);

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public GetProfileDTO getProfile(String userID) {
        User user = userRepository.findByUsernameOrEmail(userID, userID);
        return MapperUtils.mapUserToProfileDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken).getUser();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmployeeID(Integer employeeId) {
        return userRepository.findByEmployeeId(employeeId);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUserNameOrEmail(String userID) {
        return userRepository.findByUsernameOrEmail(userID, userID);
    }

    @Override
    public User updateProfile(String username, UpdateProfileDTO profile, Address address) {
        User user = userRepository.findByUsername(username);
        user.setLastUpdatedOn(new Date());
        user.setLocked(profile.isLocked());
        user.setStatus(profile.getStatus());
        return userRepository.save(user);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = getUserByUserNameOrEmail(changePasswordDTO.getUserId());
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getPassword()));
        user.setLastUpdatedOn(new Date());
        User savedUser = userRepository.save(user);

        if (savedUser != null) {
            PasswordResetToken token = passwordResetTokenRepository.findByUserUsername(savedUser.getUsername());
            token.expiredIt();
            passwordResetTokenRepository.save(token);
        }
    }

    @Override
    public void changePassword(String userEmail, String password) {
        User user = getUserByEmail(userEmail);
        user.setPassword(passwordEncoder.encode(password));
        user.setLastUpdatedOn(new Date());
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public VerificationToken createVerificationToken(User user, String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByUserUsername(user.getUsername());
        if ((verificationToken != null)) {
            if (DateUtils.isExpired(verificationToken.getExpiryDate())) {
                verificationToken.updateToken(token);
            }
        } else {
            verificationToken = new VerificationToken(token, user);
        }
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    @Transactional(readOnly = true)
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    /**
     * Generate a new verification token on request of the user. May be the existing
     * token expired so the user need another new token to enable his account.
     *
     * @param existingToken
     * @return
     */
    @Override
    public VerificationToken generateNewVerificationToken(String existingToken) {
        VerificationToken token = verificationTokenRepository.findByToken(existingToken);
        if (token != null) {
            //token.updateToken(UUID.randomUUID().toString());
            token.updateToken(Utils.getAlphaNumeric(8));
            verificationTokenRepository.save(token);
        }
        return token;
    }

    /**
     * Create a token to use in password request
     *
     * @param user
     * @param token
     */
    @Override
    public PasswordResetToken createPasswordResetToken(User user, String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByUserUsername(user.getUsername());
        logger.debug(passwordResetToken);
        if ((passwordResetToken != null)) {
            if (DateUtils.isExpired(passwordResetToken.getExpiryDate())) {
                logger.debug("update token");
                passwordResetToken.updateToken(token);
            }
        } else {
            logger.debug("create token");
            passwordResetToken = new PasswordResetToken(token, user);
        }

        return passwordResetTokenRepository.save(passwordResetToken);
    }

    /**
     * Get a PasswordResetToken object by using the token string
     *
     * @param token
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    /**
     * Get user object by using the token string
     *
     * @param token
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public User getUserByPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token).getUser();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token).getUser();
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleByName(RoleType roleType) {
        return roleRepository.findByName(roleType.name());
    }

    @Transactional
    @Override
    public void removeRoleFromUser(User user, RoleType roleType) {
        for (Role role : user.getRoles()) {
            if (role.getName().equals(roleType.toString())) {
                user.getRoles().remove(role);
                break;
            }
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void assignRoleToUser(User user, RoleType roleType) {
        Role role = roleRepository.findByName(roleType.toString());
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public List<GetProfileDTO> getAllUsers(Integer companyId) {
        List<GetProfileDTO> allUsers = new ArrayList<>();
        for (User u : userRepository.findAllByCompany_id(companyId)) {
            allUsers.add(MapperUtils.mapUserToProfileDTO(u));
        }
        return allUsers;
    }

    @Override
    public List<RoleDTO> findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> data = new ArrayList<>();

        for (Role role : roles) {
            data.add(MapperUtils.mapRoleToDTO(role));
        }

        return data;
    }
}
