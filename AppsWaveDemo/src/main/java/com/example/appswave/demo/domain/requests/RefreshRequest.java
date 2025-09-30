package com.example.appswave.demo.domain.requests;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RefreshRequest {

	@NotBlank(message = "refreshToken is required")
	private String refreshToken;
}
