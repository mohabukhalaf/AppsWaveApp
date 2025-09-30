package com.example.appswave.demo.domain.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.appswave.demo.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserTO {

	private Long id;

	@NotBlank(message = "Full name is required")
	private String fullName;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
	private String password;

	@NotNull(message = "Date of birth is required")
	private LocalDate dateOfBirth;

	@NotNull(message = "Role is required")
	private Role role;

}
