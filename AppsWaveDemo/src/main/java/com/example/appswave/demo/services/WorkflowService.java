package com.example.appswave.demo.services;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.appswave.demo.domain.enums.ActionStatus;
import com.example.appswave.demo.domain.enums.NewsStatus;

@Service
public class WorkflowService {

	public NewsStatus move(NewsStatus current, ActionStatus action) {
		if (current == NewsStatus.PENDING && action.equals(ActionStatus.APPROVE)) {
			return NewsStatus.APPROVED;
		} else if (action.equals(ActionStatus.DELETE)) {
			return NewsStatus.DELETED;
		} else if (Objects.isNull(current) && action.equals(ActionStatus.INITIATE)) {
			return NewsStatus.PENDING;
		}
		throw new IllegalStateException("Invalid transition");
	}

}
