package com.efetivoSystem.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.efetivoSystem.domain.Request;
import com.efetivoSystem.domain.RequestStage;
import com.efetivoSystem.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdatedto {

	@NotBlank(message = "Name required")
	private String name;
	
	@Email(message = "Email invalid")
	private String email;
	
	@Size(min = 7, max = 99, message = "Password must be between 7 and 99")
	private String password;
		
	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	

	public User transformToUser() {
		User user = new User(null, this.name, this.email, this.password, null, this.requests, this.stages);
		return user;
	}


}
