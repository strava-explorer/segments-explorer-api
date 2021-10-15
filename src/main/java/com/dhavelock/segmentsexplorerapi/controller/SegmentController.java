package com.dhavelock.segmentsexplorerapi.controller;

import com.dhavelock.segmentsexplorerapi.service.StravaApiService;
import com.strava.model.SummaryActivity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class SegmentController {

    private final StravaApiService stravaApiService;

    @GetMapping("/activities")
    public ResponseEntity<List<SummaryActivity>> getLatestActivities() {

        log.info("SegmentController getLatestActivities");

        return ResponseEntity.ok(
                stravaApiService.getActivities()
        );
    }
}
