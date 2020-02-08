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

import com.jnsdev.wallet.dto.UserWalletDTO;
import com.jnsdev.wallet.entity.User;
import com.jnsdev.wallet.entity.UserWallet;
import com.jnsdev.wallet.entity.Wallet;
import com.jnsdev.wallet.response.Response;
import com.jnsdev.wallet.service.UserWalletService;

@RestController
@RequestMapping("user-wallet")
public class UserWalletController {
	
	@Autowired
	private UserWalletService service;
	
	@PostMapping
	public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result) {
		
		Response<UserWalletDTO> response = new Response<UserWalletDTO>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		UserWallet uw = service.save(convertDTOToEntity(dto));
		
		response.setData(convertEntityToDTO(uw));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	private UserWalletDTO convertEntityToDTO(UserWallet uw) {
		UserWalletDTO dto = new UserWalletDTO();
		
		dto.setId(uw.getId());
		dto.setUsers(uw.getUsers().getId());
		dto.setWallet(uw.getWallet().getId());
		
		return dto;
	}

	private UserWallet convertDTOToEntity(@Valid UserWalletDTO dto) {
		UserWallet uw = new UserWallet();
		
		User u = new User();
		u.setId(dto.getUsers());
		
		Wallet w = new Wallet();
		w.setId(dto.getWallet());
		
//		uw.setId(dto.getId());
		uw.setUsers(u);
		uw.setWallet(w);
		
		return uw;
	}

}
