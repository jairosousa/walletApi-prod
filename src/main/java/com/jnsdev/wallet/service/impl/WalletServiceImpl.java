package com.jnsdev.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jnsdev.wallet.entity.Wallet;
import com.jnsdev.wallet.repository.WalletRepository;
import com.jnsdev.wallet.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository repository;

	@Override
	public Wallet save(Wallet w) {
		return repository.save(w);
	}

}
