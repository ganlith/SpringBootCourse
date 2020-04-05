package com.efetivoSystem.Service.Utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.efetivoSystem.service.utils.HashUtil;

@RunWith(SpringRunner.class)
public class HashUtilTest {
	
	@Test
	public void getSecureHash() {
		String hash = HashUtil.getSecureHash("1235");
		System.out.println("A senha criptografada é " + hash);
		
		assertThat(hash.length()).isEqualTo(64);
	}
}