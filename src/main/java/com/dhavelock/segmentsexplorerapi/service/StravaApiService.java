package com.dhavelock.segmentsexplorerapi.service;

import com.strava.api.ActivitiesApi;
import com.strava.model.ActivityType;
import com.strava.model.SummaryActivity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StravaApiService {

    private final ActivitiesApi activitiesApi;

    public List<SummaryActivity> getActivities() {
        final int now = Long.valueOf(Instant.now().getEpochSecond()).intValue();
        final int nowMinus90Days = Long.valueOf(now - 60 * 60 * 24 * 90).intValue();

        return activitiesApi.getLoggedInAthleteActivities(now, nowMinus90Days, 1, 200)
                .stream()
                .filter(activity -> ActivityType.RUN.equals(activity.getType()))
                .filter(activity -> activity.getMap() != null)
                .filter(activity -> activity.getMap().getSummaryPolyline() != null)
                .collect(Collectors.toList());
    }
}
