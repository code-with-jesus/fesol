package com.jcode.fesol.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jcode.fesol.user.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	UserAccount findByUsername(String username);
}
