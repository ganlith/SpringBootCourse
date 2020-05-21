package com.efetivoSystem.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efetivoSystem.domain.Request;
import com.efetivoSystem.domain.enums.RequestState;
import com.efetivoSystem.exception.NotFoundException;
import com.efetivoSystem.model.PageModel;
import com.efetivoSystem.model.PageRequestModel;
import com.efetivoSystem.repository.RequestRepository;

@Service
public class RequestService {
	@Autowired private RequestRepository requestRepository;
	
	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		Request createdRequest = requestRepository.save(request);
		
		return createdRequest;
		
	}
	
	public Request update(Request request) {		
		Request updatedRequest = requestRepository.save(request);
		return updatedRequest;
	}
	
	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Request for id " + id + " Not found"));
	}
	
	public List<Request> listAll() {
		List<Request> requests = requestRepository.findAll();
		return requests;
	}
	
	public PageModel<Request> listAllOnLazymode(PageRequestModel pr){		
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = requestRepository.findAll(pageable);
		
		PageModel<Request> pm = new PageModel<Request>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());		
		return pm;
		
	}
	
	public List<Request> listAllByOwnerId(Long owner) {
		List<Request> requests = requestRepository.findAllByOwnerId(owner);
		return requests;
	}
	
	public PageModel<Request> listAllByOwnerIdLazyModel(Long ownerId, PageRequestModel pr){
		
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		
		Page<Request> page = requestRepository.findAllByOwnerId(ownerId, pageable);
		
		PageModel<Request> pm = new PageModel<Request>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		
		return pm;
	}

}