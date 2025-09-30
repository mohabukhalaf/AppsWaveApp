package com.example.appswave.demo.domain.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	private String accessToken;
	private String refreshToken;
	private String tokenType = "Bearer";
	private long expiresIn; // ms until access token expires
}
