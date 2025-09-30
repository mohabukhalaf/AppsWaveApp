package com.example.appswave.demo.controllers;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appswave.demo.domain.dtos.NewsTO;
import com.example.appswave.demo.domain.enums.Role;
import com.example.appswave.demo.services.NewsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

	private final NewsService newsService;

	@GetMapping
	public ResponseEntity<List<NewsTO>> getAll(Authentication authentication) {
		String role = authentication.getAuthorities().iterator().next().getAuthority();
		return ResponseEntity.ok(newsService.getAll(Role.valueOf(role.replace("ROLE_", ""))));
	}

	@GetMapping("/{id}")
	public ResponseEntity<NewsTO> getById(@PathVariable Long id, Authentication authentication) {
		String role = authentication.getAuthorities().iterator().next().getAuthority();

		return ResponseEntity.ok(newsService.getById(id, Role.valueOf(role.replace("ROLE_", ""))));

	}

	@PostMapping
	public ResponseEntity<NewsTO> create(@RequestBody @Valid NewsTO newsTO) {
		return new ResponseEntity<>(newsService.create(newsTO), HttpStatus.CREATED);
	}

	@PostMapping("/{id}/approve")
	public ResponseEntity<String> approve(@PathVariable Long id) {
		newsService.approve(id);
		return ResponseEntity.ok("Approved");
	}

	@PutMapping("/{id}")
	public ResponseEntity<NewsTO> update(@PathVariable Long id, @RequestBody @Valid NewsTO newsTO) {
		NewsTO updatedNews = newsService.update(id, newsTO);
		if (Objects.isNull(updatedNews)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedNews);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {

		String role = authentication.getAuthorities().iterator().next().getAuthority();
		boolean deleted = newsService.delete(id, Role.valueOf(role.replace("ROLE_", "")));
		if (deleted) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
