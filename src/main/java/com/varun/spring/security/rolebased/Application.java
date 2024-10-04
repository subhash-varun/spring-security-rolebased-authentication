package com.varun.spring.security.rolebased;

import com.varun.spring.security.rolebased.model.Role;
import com.varun.spring.security.rolebased.model.User;
import com.varun.spring.security.rolebased.repository.RoleRepository;
import com.varun.spring.security.rolebased.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository userRepository, RoleRepository roleRepository) {
		return args -> {
			Role employeeRole = roleRepository.findByName("ROLE_EMPLOYEE")
					.orElse(new Role("ROLE_EMPLOYEE"));
			Role managerRole = roleRepository.findByName("ROLE_MANAGER")
					.orElse(new Role("ROLE_MANAGER"));
			Role adminRole = roleRepository.findByName("ROLE_ADMIN")
					.orElse(new Role("ROLE_ADMIN"));

			roleRepository.saveAll(Arrays.asList(employeeRole, managerRole, adminRole));

			// Update admin user if already exists
			Optional<User> existingAdmin = userRepository.findByUsername("admin");
			if (existingAdmin.isPresent()) {
				User adminUser = existingAdmin.get();
				adminUser.setPassword(new BCryptPasswordEncoder().encode("newAdminPassword"));
				adminUser.setRoles(Set.of(adminRole));
				userRepository.save(adminUser);
			} else {
				// Create new admin user if not exists
				User adminUser = new User();
				adminUser.setUsername("admin");
				adminUser.setPassword(new BCryptPasswordEncoder().encode("adminpass"));
				adminUser.setRoles(Set.of(adminRole));
				userRepository.save(adminUser);
			}

			// Update or create manager user
			Optional<User> existingManager = userRepository.findByUsername("manager");
			if (existingManager.isPresent()) {
				User managerUser = existingManager.get();
				managerUser.setPassword(new BCryptPasswordEncoder().encode("managerpass"));
				managerUser.setRoles(Set.of(managerRole));
				userRepository.save(managerUser);
			} else {
				// Create new manager user if not exists
				User managerUser = new User();
				managerUser.setUsername("manager");
				managerUser.setPassword(new BCryptPasswordEncoder().encode("managerpass"));
				managerUser.setRoles(Set.of(managerRole));
				userRepository.save(managerUser);
			}

			/*
			Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        if (userRepository.findByUsername(adminUsername) == null) {
            User adminUser = new User();
            adminUser.setUsername(adminUsername);
            adminUser.setPassword(new BCryptPasswordEncoder().encode(adminPassword));
            adminUser.setRoles(Set.of(adminRole));
            userRepository.save(adminUser);
        }

			*/
		};
	}
}
