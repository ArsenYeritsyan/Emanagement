package org.procode.management;

import org.procode.management.model.RoleEntity;
import org.procode.management.model.UserEntity;
import org.procode.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author arsen
 */
@SpringBootApplication
public class EmployeeManagementApplication implements ApplicationRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		UserEntity userEntity = new UserEntity();
		userEntity.setUsername("Arsen");
		userEntity.setPassword(passwordEncoder.encode("password"));

		new RoleEntity().addUserToRole(userEntity);
		//Set ROLE_ADMIN with its PERMISSIONS to user as default authorities.
		userRepository.save(userEntity);
	}
}
