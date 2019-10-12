package com.shopping.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;

import lombok.Data;

@Data
public class LoginDto {
	
	@NotBlank
	@Email(message = "Enter valid email")
	private String email;
	@NotBlank
	@Size(min=6,message = "Password must contain atleast 3 characters")
	private String password;
}
