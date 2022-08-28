package com.jcode.fesol.user.service;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jcode.fesol.user.model.UserAccount;
import com.jcode.fesol.user.repository.UserAccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserAccountRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount userAccount = userRepository.findByUsername(username);
		if (userAccount == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(userAccount.getUsername(), userAccount.getPassword(), emptyList());
	}
}
