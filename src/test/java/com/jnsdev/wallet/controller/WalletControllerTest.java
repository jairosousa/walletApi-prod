package com.jnsdev.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnsdev.wallet.dto.WalletDTO;
import com.jnsdev.wallet.entity.Wallet;
import com.jnsdev.wallet.response.Response;
import com.jnsdev.wallet.service.WalletService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WalletControllerTest {
	
	private static final Long ID = 1L;

	private static final String NAME = "User teste";

	private static final BigDecimal VALUE = new BigDecimal(1);
	
	private static final String URL = "/wallet";
	
	@MockBean
	WalletService service;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void testSave() throws Exception {

		BDDMockito.given(service.save(Mockito.any(Wallet.class))).willReturn(getMockWallet());

		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, VALUE))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.name").value(NAME))
				.andExpect(jsonPath("$.data.value").value(VALUE));
	}
	
	@Test
	public void testSaveNameInvalidWallet() throws JsonProcessingException, Exception {
		BDDMockito.given(service.save(Mockito.any(Wallet.class))).willReturn(getMockWallet());

		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, "12", VALUE))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors.[0]").value("O nome deve conter no mínimo 3 caracteres"));

	}
	
	@Test
	public void testSaveInvalidWalletValueNull() throws JsonProcessingException, Exception {
		BDDMockito.given(service.save(Mockito.any(Wallet.class))).willReturn(getMockWallet());
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, null))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors.[0]").value("Insira um valor para carteira"));
		
	}

	private Wallet getMockWallet() {
		Wallet w = new Wallet();
		w.setId(ID);
		w.setName(NAME);
		w.setValue(VALUE);
		return w;
	}
	
	
	private String getJsonPayload(Long id, String name, BigDecimal value) throws JsonProcessingException {
		WalletDTO dto = new WalletDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setValue(value);
		
		return new ObjectMapper().writeValueAsString(dto);
	}


}
