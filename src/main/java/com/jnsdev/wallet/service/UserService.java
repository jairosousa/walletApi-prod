package com.jnsdev.wallet.service;

import java.util.Optional;

import com.jnsdev.wallet.entity.User;

public interface UserService {

	User save(User u);

	Optional<User> findByEmail(String string);

}
