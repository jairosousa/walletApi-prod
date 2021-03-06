package com.jnsdev.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import com.jnsdev.wallet.util.enums.RoleEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jnsdev.wallet.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;

	private static final String EMAIL = "EMAIL@TESTE.COM";

	@Before
	public void setup() {
		User u = new User();
		u.setName("Set up user");
		u.setPassword("senha123");
		u.setEmail(EMAIL);
		u.setRole(RoleEnum.ROLE_ADMIN);

		repository.save(u);
	}

	@After
	public void tearDown() {
		repository.deleteAll();
	}

	@Test
	public void testSave() {
		User u = new User();
		u.setName("Teste");
		u.setPassword("123456");
		u.setEmail("teste@teste.com");
		u.setRole(RoleEnum.ROLE_ADMIN);

		User response = repository.save(u);

		assertNotNull(response);
	}

	@Test
	public void testFindByEmail() {
		Optional<User> response = repository.findByEmailEquals(EMAIL);

		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
	}

}
