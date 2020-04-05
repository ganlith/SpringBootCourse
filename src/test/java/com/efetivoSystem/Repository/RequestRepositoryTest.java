package com.efetivoSystem.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.efetivoSystem.domain.Request;
import com.efetivoSystem.domain.User;
import com.efetivoSystem.domain.enums.RequestState;
import com.efetivoSystem.repository.RequestRepository;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTest {
	@Autowired private RequestRepository requestRepository;
	

	@Test
	public void AsaveTest() {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(null, "Novo Leptop HP", "Pretendo obter um laptop hp", new Date(), RequestState.OPEN, owner, null);
		
		Request createRequest = requestRepository.save(request);
		
		assertThat(createRequest.getId()).isEqualTo(1L);
		
	}
	
	@Test
	public void updateTest() {
		
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(1L, "Novo Leptop HP", "Pretendo obter um laptop hp, de RAM 16GB",null, RequestState.OPEN, owner, null);
		
		Request updateRequest = requestRepository.save(request);
		
		assertThat(updateRequest.getDescription()).isEqualTo("Pretendo obter um laptop hp, de RAM 16GB");
	}
	
	@Test
	public void getByIdTest() {

		Optional<Request> result = requestRepository.findById(1L);
		
		Request request = result.get();
		
		assertThat(request.getSubject()).isEqualTo("Novo Leptop HP");
	}
	
	@Test
	public void listTest() {
		List<Request> requests = requestRepository.findAll();
		
		assertThat(requests.size()).isEqualTo(1);
		
	}

	@Test
	public void listByOwnerIdTest() {
		List<Request> requests = requestRepository.findAllByOwnerId(1L);
		
		assertThat(requests.size()).isEqualTo(1);
		
	}

	@Test
	public void updateStatesTest() {
		int affectedRows = requestRepository.updateStatus(1L, RequestState.CLOSED);
		
		assertThat(affectedRows).isEqualTo(1);
		
	}

}