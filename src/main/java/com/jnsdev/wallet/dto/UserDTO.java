package com.jnsdev.wallet.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)// Não adiciona valores nullos
public class UserDTO {

	private Long id;

	@NotNull
	
	@Length(min = 6, message = "A senha deve ter no minimo 6 caracteres")
	private String password;

	@Length(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
	private String name;

	@Email(message = "Email inválido")
	private String email;
}
