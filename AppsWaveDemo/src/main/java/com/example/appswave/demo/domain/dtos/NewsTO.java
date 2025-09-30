package com.example.appswave.demo.domain.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.example.appswave.demo.domain.enums.NewsStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class NewsTO {

	private Long id;

	@NotBlank(message = "Title is required")
	private String title;

	private String titleAr;

	@NotBlank(message = "Description is required")
	private String description;

	private String descriptionAr;

	private LocalDateTime publishDate;

	private String imageUrl;

	private NewsStatus status;

	@JsonIgnore
	private LocalDateTime deletedAt;

	private boolean deleteAction;
}
