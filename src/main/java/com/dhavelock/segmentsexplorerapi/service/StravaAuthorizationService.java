package com.dhavelock.segmentsexplorerapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class StravaAuthorizationService {

    private final RestTemplate restTemplate;

    @Value("${strava.token_url}")
    private String stravaTokenUrl;

    @Value("${strava.client_id}")
    private String clientId;

    @Value("${strava.client_secret}")
    private String clientSecret;

    public String getAuthorizationCode(String accessToken) {

        String url = UriComponentsBuilder.fromHttpUrl(stravaTokenUrl)
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", accessToken)
                .queryParam("grant_type", "authorization_code")
                .build()
                .toUriString();

        ResponseEntity<JsonNode> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(new HttpHeaders()),
                JsonNode.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        JsonNode responseJson = response.getBody();

        return responseJson.get("access_token").asText();
    }
}
