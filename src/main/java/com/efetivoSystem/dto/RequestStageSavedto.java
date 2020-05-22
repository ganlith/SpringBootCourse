package com.efetivoSystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.efetivoSystem.domain.Request;
import com.efetivoSystem.domain.RequestStage;
import com.efetivoSystem.domain.User;
import com.efetivoSystem.domain.enums.RequestState;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestStageSavedto {
	
	private String description;
	
	@NotNull(message = "State required")
	private RequestState state;
	
	@NotNull(message = "request required")
	private Request request;
	
	@NotNull(message = "Owner required")
	private User owner;
	

	public RequestStage transformToRequestStage() {
		
		RequestStage requestStage = new RequestStage(null, this.description, null, this.state, this.request, this.owner);
		return requestStage;
	}


}
