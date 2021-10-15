package com.dhavelock.segmentsexplorerapi.service;

import com.strava.api.ActivitiesApi;
import com.strava.model.SummaryActivity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StravaApiService {

    private final ActivitiesApi activitiesApi;

    public List<SummaryActivity> getActivities() {
        final int now = Long.valueOf(Instant.now().getEpochSecond()).intValue();
        final int nowMinus90Days = Long.valueOf(now - 60 * 60 * 24 * 90).intValue();

        return activitiesApi.getLoggedInAthleteActivities(now, nowMinus90Days, 1, 200);
    }
}
