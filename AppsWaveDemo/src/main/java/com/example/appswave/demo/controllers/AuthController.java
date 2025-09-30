package com.example.appswave.demo.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appswave.demo.domain.dtos.UserTO;
import com.example.appswave.demo.domain.requests.LoginRequest;
import com.example.appswave.demo.domain.requests.RefreshRequest;
import com.example.appswave.demo.domain.responses.AuthResponse;
import com.example.appswave.demo.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<UserTO> signup(@Valid @RequestBody UserTO userTO) {
		return new ResponseEntity<>(authService.signup(userTO), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
		return ResponseEntity.ok(authService.login(req));
	}

	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshRequest req) {
		return ResponseEntity.ok(authService.refresh(req.getRefreshToken()));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@Valid @RequestBody RefreshRequest req) {
		authService.logout(req.getRefreshToken());
		return ResponseEntity.ok().build();
	}
}
