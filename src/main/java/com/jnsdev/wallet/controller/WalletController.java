package com.jnsdev.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jnsdev.wallet.dto.WalletDTO;
import com.jnsdev.wallet.entity.Wallet;
import com.jnsdev.wallet.response.Response;
import com.jnsdev.wallet.service.WalletService;

@RestController
@RequestMapping("wallet")
public class WalletController {
	
	@Autowired
	private WalletService service;

	@PostMapping
	public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO dto, BindingResult result ) {
		
		Response<WalletDTO> response = new Response<WalletDTO>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		Wallet wallet = service.save(convertDTOToEntity(dto));
		
		response.setData(convertEntityToDTO(wallet));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	private WalletDTO convertEntityToDTO(Wallet wallet) {
		WalletDTO dto = new WalletDTO();
		dto.setId(wallet.getId());
		dto.setName(wallet.getName());
		dto.setValue(wallet.getValue());
		
		return dto;
	}

	private Wallet convertDTOToEntity(WalletDTO dto) {
		Wallet w = new Wallet();
		w.setId(dto.getId());
		w.setName(dto.getName());
		w.setValue(dto.getValue());
		
		return w;
	}

}
