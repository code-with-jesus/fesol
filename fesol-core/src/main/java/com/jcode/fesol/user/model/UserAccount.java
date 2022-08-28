package com.jcode.fesol.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_account")
public class UserAccount {

	@Id
	private String id;

	private String username;

	private String password;
}
