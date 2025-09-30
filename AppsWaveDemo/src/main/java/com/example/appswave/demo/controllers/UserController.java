package com.example.appswave.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appswave.demo.domain.dtos.UserTO;
import com.example.appswave.demo.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping
	public ResponseEntity<List<UserTO>> getAll() {
		return ResponseEntity.ok(userService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserTO> getById(@PathVariable Long id) {

		return ResponseEntity.ok(userService.getById(id));

	}

	@PostMapping
	public ResponseEntity<UserTO> create(@RequestBody @Valid UserTO userTO) {
		return new ResponseEntity<>(userService.create(userTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserTO> update(@PathVariable Long id, @RequestBody @Valid UserTO userTO) {
		UserTO updatedUser = userService.update(id, userTO);
		if (updatedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		boolean deleted = userService.delete(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
