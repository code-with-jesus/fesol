package com.jcode.fesol.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jcode.fesol.user.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	@Query(" SELECT ua FROM UserAccount ua WHERE ua.username = ?1 ")
	Optional<UserAccount> findByUsername(String username);
}
