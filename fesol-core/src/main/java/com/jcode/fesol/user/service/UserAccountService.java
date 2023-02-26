package com.jcode.fesol.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jcode.fesol.user.model.UserAccount;

public interface UserAccountService extends UserDetailsService {

	Optional<UserAccount> getUserAccount(long id);

	UserAccount save(UserAccount user);

	List<UserAccount> findAll();

	Optional<UserAccount> findByUsername(String username);
}