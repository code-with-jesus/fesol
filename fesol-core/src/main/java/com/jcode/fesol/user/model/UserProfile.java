package com.jcode.fesol.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class UserProfile {

	// Properties

	@Id
	private Integer id;

	@Column(name = "name", length = 255, unique = true, nullable = false)
	private String name;
	
}