package com.efetivoSystem.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.efetivoSystem.domain.Request;
import com.efetivoSystem.domain.RequestStage;
import com.efetivoSystem.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestSavedto {
	
	@NotBlank (message = "subject required")
	private String subject;
	
	private String description;
	
	@NotNull(message = "Owner required")
	private User owner;
	
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	

	public Request transformToRequest() {
		Request request = new Request(null, this.subject, this.description, null, null, this.owner, this.stages);
		return request;
	}

}
