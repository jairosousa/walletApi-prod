package com.jnsdev.wallet.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jnsdev.wallet.entity.Wallet;
import com.jnsdev.wallet.repository.WalletRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletServiceTest {
	
	@MockBean
	WalletRepository repository;
	
	@Autowired
	WalletService service;
	
	@Before
	public void setup() {
		BDDMockito.given(repository.save(Mockito.any()))
			.willReturn(new Wallet());
	}
	
	@Test
	public void testSave() {
		Wallet wallet = service.save(new Wallet());
		
		assertNotNull(wallet);
	}

}
