package com.efetivoSystem.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efetivoSystem.domain.RequestStage;
import com.efetivoSystem.domain.enums.RequestState;
import com.efetivoSystem.exception.NotFoundException;
import com.efetivoSystem.repository.RequestRepository;
import com.efetivoSystem.repository.RequestStageRepository;

@Service
public class RequestStageService {
	@Autowired 
	private RequestStageRepository requestStageRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	public RequestStage save(RequestStage stage) {
		stage.setRealizationDate(new Date());
		
		RequestStage createStage = requestStageRepository.save(stage);
		
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		
		requestRepository.updateStatus(requestId, state);
		
		return createStage;
				
	}
	
	public RequestStage getById(Long id) {
		Optional<RequestStage> result = requestStageRepository.findById(id);
		
		return result.orElseThrow(() -> new NotFoundException("RequestStage for id " + id + " Not found"));			
	}
	
	public List<RequestStage> listAllByRequestId(Long requestId){
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(requestId);
		return stages;
	}

}
