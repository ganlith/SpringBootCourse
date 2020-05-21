package com.efetivoSystem.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.efetivoSystem.domain.Request;
import com.efetivoSystem.domain.RequestStage;
import com.efetivoSystem.domain.User;
import com.efetivoSystem.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSavedto {
	
	@NotBlank(message = "Name required")
	private String name;
	
	@Email(message = "Email invalid")
	private String email;
	
	@Size(min = 7, max = 99, message = "Password must be between 7 and 99")
	private String password;
	
	@NotNull(message = "Role required")
	private Role role;
	
	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	public User transformToUser() {
		User user = new User(null, this.name, this.email, this.password, this.role, this.requests, this.stages);
		return user;
	}

}
