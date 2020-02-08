package com.jnsdev.wallet.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jnsdev.wallet.entity.User;
import com.jnsdev.wallet.entity.UserWallet;
import com.jnsdev.wallet.entity.Wallet;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserWalletRepositoryTest {

	@Autowired
	private UserWalletRepository repository;
	
	@Autowired
	private UserRepository uRepository;
	
	@Autowired
	private WalletRepository wRepository;

	@After
	public void tearDown() {
		repository.deleteAll();
	}

	@Test
	@Ignore
	public void testFindByid() {
		User u = new User();
		u.setName("Set up user 2");
		u.setPassword("senha123");
		u.setEmail("email2@teste.com");

		u.setId(uRepository.save(u).getId());
		
		Wallet w = new Wallet();
		w.setName("Cateira Teste 2");
		w.setValue(new BigDecimal(1));

		w.setId(wRepository.save(w).getId());

		UserWallet uw = new UserWallet();
		uw.setUsers(u);
		uw.setWallet(w);

		UserWallet userWallet = repository.save(uw);
		
		Optional<UserWallet> response = repository.findById(userWallet.getId());

		assertThat(response.isPresent(), is(true));
		assertThat(response.get().getId(), is(userWallet.getId()));
		assertEquals(response.get().getUsers(), u.getId());
		assertEquals(response.get().getWallet(), w.getId());

	}

	@Test
	public void testSave() {
		
		User u = new User();
		u.setName("Set up user");
		u.setPassword("senha123");
		u.setEmail("email@teste.com");

		uRepository.save(u);
		
		Wallet w = new Wallet();
		w.setName("Cateira Teste");
		w.setValue(new BigDecimal(1));

		wRepository.save(w);

		UserWallet uw = new UserWallet();
		uw.setUsers(u);
		uw.setWallet(w);

		UserWallet response = repository.save(uw);

		assertThat(response.getId(), is(1L));
		assertNotNull(response);
	}

}
