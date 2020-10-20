package com.prakat.middleware;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.prakat.middleware.config.AppProperties;
import com.prakat.middleware.entity.AuthProvider;
import com.prakat.middleware.entity.Role;
import com.prakat.middleware.entity.User;
import com.prakat.middleware.repository.UserRepository;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MiddlewareApplication implements CommandLineRunner{
	@Autowired
	UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Value("${user.name}")
	private String userName;
	@Value("${user.authProvider}")
	private AuthProvider userAuthProvider;
	@Value("${user.password}")
	private String userPassword;
	@Value("${user.emailId}")
	private String userEmailId;
	@Value("${user.role}")
	private Role userRole;
	@Value("${user.imageUrl}")
	private String userImageUrl;
	
	@Value("${admin.name}")
	private String adminName;
	@Value("${admin.authProvider}")
	private AuthProvider adminAuthProvider;
	@Value("${admin.password}")
	private String adminPassword;
	@Value("${admin.emailId}")
	private String adminEmailId;
	@Value("${admin.role}")
	private Role adminRole;
	@Value("${admin.imageUrl}")
	private String adminImageUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(MiddlewareApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setEmail(userEmailId);
		user.setImageUrl(userImageUrl);
		user.setName(userName);
		user.setPassword(passwordEncoder.encode(userPassword));
		user.setProvider(userAuthProvider);
		user.setRole(userRole);
		Optional<User> userOptional = repository.findByEmail(userEmailId);
		if(! userOptional.isPresent()) {
			repository.save(user);
		}
		
		User admin = new User();
		admin.setEmail(adminEmailId);
		admin.setImageUrl(adminImageUrl);
		admin.setName(adminName);
		admin.setPassword(passwordEncoder.encode(adminPassword));
		admin.setProvider(adminAuthProvider);
		admin.setRole(adminRole);
		Optional<User> adminOptional = repository.findByEmail(adminEmailId);
		if(! adminOptional.isPresent()) {
			repository.save(admin);
		}
	}
}
