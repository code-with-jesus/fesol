package com.jcode.fesol.user.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

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
	
	@NotEmpty
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
        name = "user_account_user_profile",
        joinColumns = { @JoinColumn (name = "user_account_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "user_profile_id") }
    )
	private Set<UserProfile> userProfiles = new HashSet<>();
}
