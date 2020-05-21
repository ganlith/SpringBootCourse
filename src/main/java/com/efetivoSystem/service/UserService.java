package com.efetivoSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efetivoSystem.domain.User;
import com.efetivoSystem.exception.NotFoundException;
import com.efetivoSystem.model.PageModel;
import com.efetivoSystem.model.PageRequestModel;
import com.efetivoSystem.repository.UserRepository;
import com.efetivoSystem.service.utils.HashUtil;

@Service
public class UserService {
	@Autowired private UserRepository userRepository;
	
	public User save(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User createUser = userRepository.save(user);
		return createUser;
	}
	
	public User update(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	public User getById(Long id) {
		Optional<User> result = userRepository.findById(id);
		
		return result.orElseThrow(() -> new NotFoundException("User for id " + id + " Not found"));
	}
	
	public List<User> listAll() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
	public PageModel<User> listAllOnLazymode(PageRequestModel pr){		
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<User> page = userRepository.findAll(pageable);
		
		PageModel<User> pm = new PageModel<User>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());		
		return pm;
		
	}
	
	public User login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		
		Optional<User> result = userRepository.login(email, password);
		
		return result.get();
	}
	
	public int updateRole(User user) {
		return userRepository.updateRole(user.getId(), user.getRole());
	}

}