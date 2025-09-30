package com.example.appswave.demo.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.appswave.demo.domain.dtos.NewsTO;
import com.example.appswave.demo.domain.entities.News;
import com.example.appswave.demo.domain.enums.ActionStatus;
import com.example.appswave.demo.domain.enums.NewsStatus;
import com.example.appswave.demo.domain.enums.Role;
import com.example.appswave.demo.domain.transformers.INewsConvertor;
import com.example.appswave.demo.repositories.INewsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsService {

	private final INewsRepository newsRepository;
	private final INewsConvertor newsConvertor;
	public final WorkflowService workflowService;

	public List<NewsTO> getAll(Role currentUserRole) {
		if (currentUserRole == Role.NORMAL) {
			return newsConvertor.toTOs(newsRepository.findAllByStatus(NewsStatus.APPROVED));
		} else {
			return newsConvertor.toTOs(newsRepository.findAll());
		}
	}

	public NewsTO getById(Long id, Role currentUserRole) {
		if (currentUserRole == Role.NORMAL) {
			return newsRepository.findByIdAndStatus(id, NewsStatus.APPROVED).map(newsConvertor::toTO).orElse(null);
		} else {
			return newsRepository.findById(id).map(newsConvertor::toTO).orElse(null);
		}

	}

	@Transactional
	public NewsTO create(NewsTO newsTO) {

		newsTO.setId(null);
		newsTO.setPublishDate(LocalDateTime.now());
		newsTO.setStatus(workflowService.move(newsTO.getStatus(), ActionStatus.INITIATE));
		newsTO.setDeletedAt(null);
		newsTO.setDeleteAction(false);

		return newsConvertor.toTO(newsRepository.save(newsConvertor.toEntity(newsTO)));
	}

	@Transactional
	public void approve(Long id) {
		Optional<News> news = newsRepository.findById(id);

		news.ifPresent(n -> {
			n.setStatus(workflowService.move(n.getStatus(), ActionStatus.APPROVE));
			newsRepository.save(n);
		});

	}

	// UPDATE
	@Transactional
	public NewsTO update(Long id, NewsTO newsTO) {
		Optional<News> existingNewsOpt = newsRepository.findById(id);

		if (existingNewsOpt.isPresent()) {
			News existingNews = existingNewsOpt.get();

			// update fields
			existingNews.setDescription(newsTO.getDescription());
			existingNews.setDescriptionAr(newsTO.getDescriptionAr());
			existingNews.setTitle(newsTO.getTitle());
			existingNews.setTitleAr(newsTO.getTitleAr());
			existingNews.setImageUrl(newsTO.getImageUrl());

			return newsConvertor.toTO(newsRepository.save(existingNews));
		}

		return null;
	}

	// DELETE
	@Transactional
	public boolean delete(Long id, Role currentUserRole) {
		Optional<News> news = newsRepository.findById(id);
		if (news.isPresent()) {
			News deleteExistedNews = news.get();

			// If user is a CONTENT_WRITER
			if (currentUserRole == Role.CONTENT_WRITER) {
				if (deleteExistedNews.getStatus() != NewsStatus.PENDING) {
					deleteExistedNews.setDeleteAction(true);
					newsRepository.save(deleteExistedNews);
					return true;
				}
			}
			deleteNews(deleteExistedNews);
			return true;
		}
		return false;
	}

	@Transactional
	public void deleteOldNewsList() {
		List<News> expired = newsRepository.findByPublishDateBeforeAndStatusNot(LocalDateTime.now(),
				NewsStatus.DELETED);
		for (News n : expired) {
			deleteNews(n);
		}
	}

	@Transactional
	public void deleteNews(News news) {

		news.setStatus(workflowService.move(news.getStatus(), ActionStatus.DELETE));
		news.setDeletedAt(LocalDateTime.now());
		news.setDeleteAction(false);
		newsRepository.save(news);
	}

}
