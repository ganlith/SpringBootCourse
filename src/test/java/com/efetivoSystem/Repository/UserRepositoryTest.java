package com.efetivoSystem.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.efetivoSystem.domain.User;
import com.efetivoSystem.domain.enums.Role;
import com.efetivoSystem.repository.UserRepository;


@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTest {
	@Autowired private UserRepository userRepository;
	
	@Ignore
	public void AsaveTest() {
		User user = new User(null, "Marcelo","marcelo.schoeffel@gmail.com", "1234", Role.ADMINISTRATOR, null, null);
		User createUser = userRepository.save(user);
		
		assertThat(createUser.getId()).isEqualTo(1L);
		
	}
	
	@Ignore
	public void updateTest() {
		User user = new User(1L, "Marcelo Schoeffel","marcelo.schoeffel@hotmail.com", "1234", Role.ADMINISTRATOR, null, null);
		User updateUser = userRepository.save(user);
		
		assertThat(updateUser.getName()).isEqualTo("Marcelo Schoeffel");
	}
	
	@Ignore
	public void getByIdTest() {
		Optional<User> result = userRepository.findById(1L);
		User user = result.get();

		assertThat(user.getPassword()).isEqualTo("1234");
	}
	
	@Ignore
	public void listTest() {
		List<User> users = userRepository.findAll();
		
		assertThat(users.size()).isEqualTo(1);
	}
	
	@Ignore
	public void loginTest() {
		Optional<User> result = userRepository.login("marcelo.schoeffel@gmail.com", "1234");
		User loggedUser = result.get();
		
		assertThat(loggedUser.getId()).isEqualTo(1L);				
		
	}
	
	@Test
	public void updateRoleTest() {
		
		int affectRows = userRepository.updateRole(4L, Role.ADMINISTRATOR);
		assertThat(affectRows).isEqualTo(1);
		
	}

}