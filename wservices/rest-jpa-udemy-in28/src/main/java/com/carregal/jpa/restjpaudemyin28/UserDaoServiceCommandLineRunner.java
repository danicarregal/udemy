package com.carregal.jpa.restjpaudemyin28;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.carregal.jpa.restjpaudemyin28.entity.User;
import com.carregal.jpa.restjpaudemyin28.service.SpringDataUserRepository;
import com.carregal.jpa.restjpaudemyin28.service.UserDAOService;

@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {

	@Autowired
	private UserDAOService userService;

	@Autowired
	private SpringDataUserRepository userRepo;

	private static Logger logger = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user = new User("Daniel", "admin");
		// Integer id = userService.insert(user);
		User savedUser = userRepo.save(user);
		/*
		 * System.out.println(String.format("User %d inserted", id));
		 * logger.info(String.format("User %d inserted", id));
		 */

		// Optional<User> userRetrieved = userRepo.findById(23);
		// logger.info(String.format("Se ha encontrado un usuario ",
		// userRetrieved.get().getName()));

		User userRetrieved = userRepo.findById(23).orElse(null);
		logger.info(userRetrieved != null ? "Se ha encontrado un usuario" : "No existe el usuario");
		List<User> users = userRepo.findAll();
		logger.info("Usuarios: " + users);

	}

}
