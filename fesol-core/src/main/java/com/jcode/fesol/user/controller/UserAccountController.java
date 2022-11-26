package com.jcode.fesol.user.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jcode.fesol.user.model.UserAccount;
import com.jcode.fesol.user.repository.UserAccountRepository;

@RestController("/v1/users")
public class UserAccountController {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public List<UserAccount> getAllUserAccounts() {
		return userAccountRepository.findAll();
	}

	@GetMapping("/{username}")
	public UserAccount getUserAccount(@PathVariable String username) {
		return userAccountRepository.findByUsername(username);
	}
	
	@PostMapping
	public void saveUserAccount(@RequestBody UserAccount userAccount) {
		userAccount.setId(UUID.randomUUID().toString());
		userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
		userAccountRepository.save(userAccount);
	}
}
