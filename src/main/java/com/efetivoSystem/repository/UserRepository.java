package com.efetivoSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.efetivoSystem.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM user u WHERE email = ?1 and password = ?2")
	public Optional<User> login(String email, String password);

}