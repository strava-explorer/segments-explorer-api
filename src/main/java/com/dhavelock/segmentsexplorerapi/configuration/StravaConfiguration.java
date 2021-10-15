package com.dhavelock.segmentsexplorerapi.configuration;

import com.strava.api.ActivitiesApi;
import com.strava.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.strava.api.SegmentsApi;
import org.springframework.web.client.RestTemplate;

@Configuration
public class StravaConfiguration {

    @Bean("stravaRestTemplate")
    public RestTemplate stravaRestTemplate() {
        return new RestTemplateBuilder()
                .build();
    }

    @Bean("stravaApiClient")
    public ApiClient stravaApiClient(@Qualifier("stravaRestTemplate") RestTemplate restTemplate) {
        return new ApiClient(restTemplate);
    }

    @Bean("segmentsApi")
    public SegmentsApi segmentsApi(@Qualifier("stravaApiClient") ApiClient apiClient) {
        return new SegmentsApi(apiClient);
    }

    @Bean("activitiesApi")
    public ActivitiesApi activitiesApi(@Qualifier("stravaApiClient") ApiClient apiClient) {
        return new ActivitiesApi(apiClient);
    }
}
