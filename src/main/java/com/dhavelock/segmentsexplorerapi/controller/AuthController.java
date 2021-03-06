package com.dhavelock.segmentsexplorerapi.controller;

import com.dhavelock.segmentsexplorerapi.model.AuthTokenDto;
import com.dhavelock.segmentsexplorerapi.service.StravaAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorize")
@RequiredArgsConstructor
public class AuthController {

    private final StravaAuthorizationService stravaAuthorizationService;

    @PostMapping("/token")
    public ResponseEntity<AuthTokenDto> getAuthorizationToken(
            @RequestParam String accessToken
    ) {

        if (!accessToken.matches("[a-zA-Z0-9]*")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        final String authorizationCode = stravaAuthorizationService.getAuthorizationCode(accessToken);

        return ResponseEntity.ok(
                AuthTokenDto.builder()
                        .authToken(authorizationCode)
                        .build()
        );
    }
}
