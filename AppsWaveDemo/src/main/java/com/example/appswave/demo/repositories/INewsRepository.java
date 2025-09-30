package com.example.appswave.demo.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.appswave.demo.domain.entities.News;
import com.example.appswave.demo.domain.enums.NewsStatus;

public interface INewsRepository extends JpaRepositoryImplementation<News, Long> {

	List<News> findByPublishDateBeforeAndStatusNot(LocalDateTime now, NewsStatus expired);

	List<News> findAllByStatus(NewsStatus status);

	Optional<News> findByIdAndStatus(Long id, NewsStatus status);

}
