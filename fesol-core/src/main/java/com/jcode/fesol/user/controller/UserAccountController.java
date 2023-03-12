package com.jcode.fesol.user.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcode.fesol.user.dto.UserDto;
import com.jcode.fesol.user.model.UserAccount;
import com.jcode.fesol.user.service.UserAccountService;

@RestController
@RequestMapping(value = "/v1/users")
public class UserAccountController {

    @Autowired
    @Qualifier("userDetailsService")
    private UserAccountService userAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<UserDto> getAllUserAccounts() {
        return userAccountService.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserAccount> findUserAccount(@PathVariable String username) {
        Optional<UserAccount> userAccountOptional = userAccountService.findByUsername(username);
        if (userAccountOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userAccountOptional.get());
    }

    @PostMapping
    public void saveUserAccount(@RequestBody UserAccount userAccount) {
        userAccount.setId(UUID.randomUUID().toString());
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccountService.save(userAccount);
    }
}
