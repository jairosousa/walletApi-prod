package com.jnsdev.wallet.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jnsdev.wallet.entity.Wallet;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletRepositoryTest {

	@Autowired
	private WalletRepository repository;
	
	private static final String NAME = "Teste 1";

	private Wallet wallet;
	
	@Before
	public void setup() {
		Wallet w = new Wallet();
		w.setName(NAME);
		w.setValue(new BigDecimal(1));

		wallet = repository.save(w);
	}

	@After
	public void tearDown() {
		repository.deleteAll();
	}

	@Test
	@WithMockUser
	public void testFindByid() {
		Optional<Wallet> response = repository.findById(wallet.getId());
		
		assertThat(response.isPresent(), is(true));
		assertThat(response.get().getId(), is(wallet.getId()));
		assertEquals(response.get().getName(), NAME);
		assertNotNull(response.get().getValue());
		
	}
	
	@Test
	public void testSave() {
		Wallet w = new Wallet();
		w.setName("Jairo");
		w.setValue(new BigDecimal(1));

		Wallet response = repository.save(w);

//		assertThat(w.getId(), is(2L));
		assertNotNull(response);
	}
	

}
