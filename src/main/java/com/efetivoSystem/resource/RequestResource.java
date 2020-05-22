package com.efetivoSystem.resource;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efetivoSystem.domain.Request;
import com.efetivoSystem.domain.RequestStage;
import com.efetivoSystem.dto.RequestSavedto;
import com.efetivoSystem.dto.RequestUpdatedto;
import com.efetivoSystem.model.PageModel;
import com.efetivoSystem.model.PageRequestModel;
import com.efetivoSystem.service.RequestService;
import com.efetivoSystem.service.RequestStageService;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {
	@Autowired private RequestService requestService;
	@Autowired private RequestStageService stagetService;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody @Valid RequestSavedto requestdto){
		
		Request request = requestdto.transformToRequest();
		
		Request createdRequest = requestService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody @Valid RequestUpdatedto requestdto){
		
		Request request = requestdto.transformToRequest();		
		request.setId(id);		
		Request updateRequest = requestService.update(request);		
		return ResponseEntity.ok(updateRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id){
		Request request = requestService.getById(id);
		return ResponseEntity.ok(request);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<Request>> listAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllOnLazymode(pr);
		
		return ResponseEntity.ok(pm);
		
	}
	
	@GetMapping("/{id}/request-stage")
	public ResponseEntity<PageModel<RequestStage>> listAllStageById(
			@PathVariable(name = "id") Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<RequestStage> pm = stagetService.listAllByRequestIdLazyModel(id, pr);
		return ResponseEntity.ok(pm);
	}

}
