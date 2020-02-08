package com.jnsdev.wallet.service;

import java.util.Optional;

import com.jnsdev.wallet.entity.UserWallet;

public interface UserWalletService {
	
	UserWallet save(UserWallet userWallet);
	
	Optional<UserWallet> findByUsersIdAndWalletId(Long user, long wallet);

}
