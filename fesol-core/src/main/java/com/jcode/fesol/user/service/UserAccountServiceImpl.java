package com.jcode.fesol.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jcode.fesol.user.model.UserAccount;
import com.jcode.fesol.user.repository.UserAccountRepository;

@Service("userDetailsService")
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserAccount> userAccountOptional = this.findByUsername(username);
		if (userAccountOptional.isEmpty()) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return UserDetailsMapper.build(userAccountOptional.get());
	}

	@Override
	public Optional<UserAccount> getUserAccount(long id) {
		return userRepository.findById(id);
	}

	@Override
	public UserAccount save(UserAccount userAccount) {
		return userRepository.save(userAccount);
	}

	@Override
	public List<UserAccount> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserAccount> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
