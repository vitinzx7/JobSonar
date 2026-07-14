package com.project.jobsonnar.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.project.jobsonnar.dto.jooble.JoobleSearchResponseDto;
import com.project.jobsonnar.dto.jooble.JoobleSearchRequestDto;

@Component
public class JoobleJobProvider {
    private final String apiKey;
    private final RestClient restClient;

    public JoobleJobProvider(@Value("${jooble.api.key}") String apiKey, RestClient.Builder restClientBuilder) {
        this.apiKey = apiKey;
        this.restClient = restClientBuilder
            .baseUrl("https://jooble.org")
            .build();
    }

    private JoobleSearchRequestDto buildJoobleSearchRequestDto(String query) {
        JoobleSearchRequestDto request = new JoobleSearchRequestDto();

        request.setKeywords(query);
        request.setLocation("Brazil");
        request.setPage("1");
        request.setResultOnPage("5");

        return request;
    }

    public JoobleSearchResponseDto searchJobs(String query) {
        JoobleSearchRequestDto request = buildJoobleSearchRequestDto(query);

        return restClient
            .post()
            .uri("/api/{apiKey}", apiKey)
            .body(request)
            .retrieve()
            .body(JoobleSearchResponseDto.class);
    }
}
