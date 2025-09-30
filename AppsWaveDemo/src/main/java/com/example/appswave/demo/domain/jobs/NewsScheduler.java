package com.example.appswave.demo.domain.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.appswave.demo.services.NewsService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NewsScheduler {

	private final NewsService newsService;

	@Scheduled(cron = "${scheduler.delete.old.news}")
	public void deleteOldNews() {
		newsService.deleteOldNewsList();
	}
}
