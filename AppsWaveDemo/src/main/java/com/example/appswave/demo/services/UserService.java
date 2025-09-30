package com.example.appswave.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.appswave.demo.domain.dtos.UserTO;
import com.example.appswave.demo.domain.entities.User;
import com.example.appswave.demo.domain.transformers.IUserConvertor;
import com.example.appswave.demo.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final IUserRepository userRepository;
	private final IUserConvertor userConvertor;
	private final PasswordEncoder passwordEncoder;

	// CREATE
	@Transactional
	public UserTO create(UserTO userTO) {
		userTO.setId(null);
		User user = userConvertor.toEntity(userTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userConvertor.toTO(userRepository.save(user));
	}

	// READ - Get All
	public List<UserTO> getAll() {
		return userConvertor.toTOs(userRepository.findAll());
	}

	// READ - Get by ID
	public UserTO getById(Long id) {
		return userRepository.findById(id).map(userConvertor::toTO).orElse(null);
	}

	// UPDATE
	@Transactional
	public UserTO update(Long id, UserTO userTO) {
		Optional<User> existingUserOpt = userRepository.findById(id);

		if (existingUserOpt.isPresent()) {
			User existingUser = existingUserOpt.get();

			// update fields
			existingUser.setFullName(userTO.getFullName());
			existingUser.setEmail(userTO.getEmail());
			existingUser.setPassword(passwordEncoder.encode(userTO.getPassword()));
			existingUser.setDateOfBirth(userTO.getDateOfBirth());
			existingUser.setRole(userTO.getRole());

			return userConvertor.toTO(userRepository.save(existingUser));
		}

		return null;
	}

	// DELETE
	@Transactional
	public boolean delete(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
