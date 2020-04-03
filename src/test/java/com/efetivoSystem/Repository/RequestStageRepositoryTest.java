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
import com.efetivoSystem.domain.RequestStage;
import com.efetivoSystem.domain.User;
import com.efetivoSystem.domain.enums.RequestState;
import com.efetivoSystem.repository.RequestStageRepository;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestStageRepositoryTest {
	@Autowired private RequestStageRepository requestStageRepository;
	
	@Test
	public void AsaveTeste() {
		User owner = new User();
		owner.setId(1L); 
		
		Request request = new Request();
		request.setId(1L);
		
		RequestStage stage = new RequestStage(1L, "Foi combrado um laptop da marca HP com 16 GB de RAM", new Date(), RequestState.CLOSED, request, owner);
		

		RequestStage  createStage = requestStageRepository.save(stage);
		
		assertThat(createStage.getId()).isEqualTo(1L);
		
	}
	
	@Test
	public void getByIdTest() {

		Optional<RequestStage> result = requestStageRepository.findById(1L);
		
		RequestStage stage = result.get();
		
		assertThat(stage.getDescription()).isEqualTo("Foi combrado um laptop da marca HP com 16 GB de RAM");
	}
	
	@Test
	public void listByRequestIdTest() {
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(1L);
		

		assertThat(stages.size()).isEqualTo(1);
	}

}