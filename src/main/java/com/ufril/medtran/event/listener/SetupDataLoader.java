package com.ufril.medtran.event.listener;

import com.ufril.medtran.enumeration.PrivilegeType;
import com.ufril.medtran.enumeration.RoleType;
import com.ufril.medtran.persistence.domain.account.Privilege;
import com.ufril.medtran.persistence.domain.account.Role;
import com.ufril.medtran.persistence.domain.account.User;
import com.ufril.medtran.persistence.repository.account.PrivilegeRepository;
import com.ufril.medtran.persistence.repository.account.RoleRepository;
import com.ufril.medtran.persistence.repository.account.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//import com.ufril.helper.GeoLocationHelper;

/**
 * @author moin
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private static Logger logger = Logger.getLogger(SetupDataLoader.class);

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PrivilegeRepository privilegeRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Transactional
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		if (alreadySetup) {
			return;
		}
		//createSystemPrivilegesAndRoles();
//		createAdminAccount();
		// Create a default user with 'ADMIN' and 'USER' role
		//createAdministrator();

//		createTestUserAccounts(10);

		alreadySetup = true;

	}


	private void createSystemPrivilegesAndRoles() {
		// == create initial privileges
		final Privilege readPrivilege = createPrivilegeIfNotFound(PrivilegeType.READ_PRIVILEGE.name());
		final Privilege writePrivilege = createPrivilegeIfNotFound(PrivilegeType.WRITE_PRIVILEGE.name());

		// == create initial roles
		final List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
		final List<Privilege> userPrivileges = Arrays.asList(readPrivilege, writePrivilege);

		createRoleIfNotFound(RoleType.Administrator.name(), adminPrivileges);
		createRoleIfNotFound(RoleType.Dispatcher.name(), userPrivileges);
	}

	private void createAdminAccount() {

		final Role userRole = roleRepository.findByName(RoleType.Dispatcher.name());
		final Role adminRole = roleRepository.findByName(RoleType.Administrator.name());
		final ArrayList roleArray = new ArrayList();
		roleArray.add(userRole);
		roleArray.add(adminRole);

		if (userRepository.existsByUsername("admin")) {
			User user = userRepository.findByUsername("admin");
			user.setPassword(passwordEncoder.encode(user.getUsername()));
			user.setStatus("ACTIVE");
			user.setEmail("admin@giftapp.com");
			user.setRoles(roleArray);
			user.setLastUpdatedOn(new Date());
			userRepository.save(user);
		}
	}

	private void createAdministrator() {

		final Role adminRole = roleRepository.findByName(RoleType.Administrator.name());
		final Role userRole = roleRepository.findByName(RoleType.Dispatcher.name());

		// admin
		if (!userRepository.existsByUsername("admin")) {
			final User adminUser = new User();
			adminUser.setUsername("admin");
			adminUser.setPassword(passwordEncoder.encode("admin123"));
			adminUser.setEmail("admin@ufril.com");
			adminUser.setStatus("ACTIVE");
			adminUser.setRoles(Arrays.asList(adminRole, userRole));
			adminUser.setLastUpdatedOn(new Date());
			userRepository.save(adminUser);
		}
//		for (int i = 1; i < 11; i++) {
//			String userName="testuser"+i;
//			if (!userRepository.exists(userName)) {
//				final User user = new User();
//				user.setUsername(userName);
//				user.setFirstName(userName);
//				user.setLastName(userName);
//				user.setPassword(passwordEncoder.encode(userName));
//				user.setEmail(userName+"@ufril.com");
//				user.setRoles(Arrays.asList( userRole));
//				user.setEnabled(true);
//				user.setCreatedOn(new Date());
//				user.setLastUpdatedOn(new Date());
//				userRepository.save(user);
//			}
//		}

	}

	private Privilege createPrivilegeIfNotFound(final String name) {
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilege = privilegeRepository.save(privilege);
		}
		return privilege;
	}

	private Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			role = roleRepository.save(role);
		}
		return role;
	}
}
