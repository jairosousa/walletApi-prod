package com.jnsdev.wallet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.jnsdev.wallet.util.enums.RoleEnum;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 5736429133665309117L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String email;
	
//	@NotNull
//	@Enumerated(EnumType.STRING)
//	private RoleEnum role;
}
