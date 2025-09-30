package com.example.appswave.demo.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.appswave.demo.domain.enums.NewsStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7283817345265010765L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	private String titleAr;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;
	@Column(columnDefinition = "TEXT")
	private String descriptionAr;

	@Column(nullable = false)
	private LocalDateTime publishDate;
	private String imageUrl;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private NewsStatus status;

	private boolean deleteAction;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@CreationTimestamp
	private LocalDateTime createdAt;

	private LocalDateTime deletedAt;
}
