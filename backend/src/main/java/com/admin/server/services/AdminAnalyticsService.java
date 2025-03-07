package com.admin.server.services;
import com.admin.server.repositories.AdminAnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminAnalyticsService {

    @Autowired
    private AdminAnalyticsRepository adminAnalyticsRepository;

    public long getNewUsersCount(Date startDate) {
        return adminAnalyticsRepository.countNewUsersInPeriod(startDate);
    }

    public Map<String, Long> getUserCountByRole() {
        List<Object[]> results = adminAnalyticsRepository.getUserCountByRole();
        return results.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }}