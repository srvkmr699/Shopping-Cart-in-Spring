package com.shopping.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {
	
	
	private int id;
	
	@NotBlank
	@Size(min=5)
	private String firstName;
	
	@NotBlank
	@Size(min=3)
	private String lastName;
	
	@NotBlank
	@Email(message = "Enter valid email")
	private String email;
	
	@NotBlank
	@Size(min=6)
	private String password;
}
