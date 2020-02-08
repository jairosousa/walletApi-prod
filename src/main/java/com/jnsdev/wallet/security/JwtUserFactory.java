package com.jnsdev.wallet.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.jnsdev.wallet.entity.User;
import com.jnsdev.wallet.util.enums.RoleEnum;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtUserFactory {

//	public static JwtUser create(User user) {
//		return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), createGrantedAuthorities(user.getRole()));
//	}
	
	public static JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getEmail(), user.getPassword());
	}
	
//	private static List<GrantedAuthority> createGrantedAuthorities(RoleEnum role) {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority(role.toString()));
//		return authorities;
//	}

}
