package com.ufril.medtran.service.impl;

import com.ufril.medtran.enumeration.RoleType;
import com.ufril.medtran.event.OnLoginSuccessEvent;
import com.ufril.medtran.persistence.domain.account.Privilege;
import com.ufril.medtran.persistence.domain.account.Role;
import com.ufril.medtran.persistence.domain.account.User;
import com.ufril.medtran.persistence.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by moin on 11/3/15.
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

    private final UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search may possibly be case
     * sensitive, or case insensitive depending on how the implementation instance is configured. In this case, the
     * <code>UserDetails</code> object that comes back may have a username that is of a different case than what was
     * actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            logger.debug("USER ID : "+ username);
            User user = userService.getUserByUserNameOrEmail(username.toLowerCase());
            logger.debug("USER : "+user);
//            todo fix getroles
            if (user == null) {
                return new org.springframework.security.core.userdetails.User(
                        " ", " ", true, true, true, true,
                        getAuthorities(user.getRoles()));
            }

            eventPublisher.publishEvent(new OnLoginSuccessEvent(user));

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), user.isUserActive(), true, true, true,
                    getAuthorities(user.getRoles()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }


    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<String>();
        final List<Privilege> collection = new ArrayList<Privilege>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }

        //for (final Privilege item : collection) {
        //    privileges.add(item.getName());
        //}
        // equivalent to the above for loop
        privileges.addAll(collection.stream().map(Privilege::getName).collect(Collectors.toList()));
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        //final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //for (final String privilege : privileges) {
        //    authorities.add(new SimpleGrantedAuthority(privilege));
        //}
        // equivalent to the above for loop
        return privileges.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
