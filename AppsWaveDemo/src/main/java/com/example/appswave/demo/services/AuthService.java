package com.example.appswave.demo.services;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.appswave.demo.domain.dtos.UserTO;
import com.example.appswave.demo.domain.entities.RefreshToken;
import com.example.appswave.demo.domain.entities.User;
import com.example.appswave.demo.domain.requests.LoginRequest;
import com.example.appswave.demo.domain.responses.AuthResponse;
import com.example.appswave.demo.domain.transformers.IUserConvertor;
import com.example.appswave.demo.repositories.IUserRepository;
import com.example.appswave.demo.repositories.RefreshTokenRepository;
import com.example.appswave.demo.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final IUserRepository userRepository;
	private final IUserConvertor userConvertor;
	private final RefreshTokenRepository refreshTokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Transactional
	public UserTO signup(UserTO userTO) {
		if (userRepository.existsByEmail(userTO.getEmail())) {
			throw new RuntimeException("Email already in use");
		}
		userTO.setId(null);
		User user = userConvertor.toEntity(userTO);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User saved = userRepository.save(user);

		return userConvertor.toTO(saved);
	}

	@Transactional
	public AuthResponse login(LoginRequest req) {

		User user = userRepository.findByEmail(req.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		// ✅ check password
		if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
			throw new BadCredentialsException("Invalid email or password");
		}

		String accessToken = jwtUtil.generateAccessToken(user);
		RefreshToken refreshToken = createRefreshToken(user);

		AuthResponse resp = new AuthResponse();
		resp.setAccessToken(accessToken);
		resp.setRefreshToken(refreshToken.getToken());
		resp.setExpiresIn(jwtUtil.getJwtExpirationMs());
		return resp;
	}

	private RefreshToken createRefreshToken(User user) {
		// delete existing tokens if you want single-session
		refreshTokenRepository.deleteByUser(user);

		String token = UUID.randomUUID().toString();
		RefreshToken rt = RefreshToken.builder().token(token).user(user)
				.expiryDate(LocalDateTime.now().plusNanos(jwtUtil.getRefreshExpirationMs() * 1_000_000L)).revoked(false)
				.build();
		return refreshTokenRepository.save(rt);
	}

	public AuthResponse refresh(String refreshTokenStr) {
		RefreshToken rt = refreshTokenRepository.findByToken(refreshTokenStr)
				.orElseThrow(() -> new RuntimeException("Invalid refresh token"));

		if (rt.isRevoked() || rt.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("Refresh token expired or revoked");
		}

		User user = rt.getUser();
		String accessToken = jwtUtil.generateAccessToken(user);

		// Optionally rotate refresh token
		refreshTokenRepository.delete(rt);
		RefreshToken newRt = createRefreshToken(user);

		AuthResponse response = new AuthResponse();
		response.setAccessToken(accessToken);
		response.setRefreshToken(newRt.getToken());
		response.setExpiresIn(jwtUtil.getJwtExpirationMs());
		return response;
	}

	@Transactional
	public void logout(String refreshTokenStr) {
		refreshTokenRepository.findByToken(refreshTokenStr).ifPresent(rt -> {
			rt.setRevoked(true);
			refreshTokenRepository.save(rt);
		});
	}

}
