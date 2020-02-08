package com.jnsdev.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jnsdev.wallet.entity.UserWallet;
import com.jnsdev.wallet.repository.UserWalletRepository;
import com.jnsdev.wallet.service.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService {

	@Autowired
	private UserWalletRepository repository;

	@Override
	public UserWallet save(UserWallet userWallet) {
		return repository.save(userWallet);
	}

	@Override
	public Optional<UserWallet> findByUsersIdAndWalletId(Long user, long wallet) {
		return repository.findByUsersIdAndWalletId(user, wallet);
	}

}
