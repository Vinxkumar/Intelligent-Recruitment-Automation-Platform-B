package com.example.RecruitmentAutomationPlatform.microservice;

import com.example.RecruitmentAutomationPlatform.dto.JobDto.JdRequest;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JdResponse;
import com.example.RecruitmentAutomationPlatform.exceptions.JdGenerateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;
@Slf4j
@Component
public class JdGenerator {

    private final RestClient restClient;

    public JdGenerator(@Value("${microservice.url}") String uri) {
        this.restClient = RestClient.create(uri);
    }



    public JdResponse generateJd(JdRequest job) {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(job);
        log.info("[DEBUG] Sending raw JSON: {}", json);

        return restClient.post()
                .uri("/api/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .retrieve()
                .onStatus(status -> status.value() == 422, (request, response) -> {
                    String body = new String(response.getBody().readAllBytes());
                    log.error("FastAPI validation error: {}", body);
                    throw new JdGenerateException("Validation failed: " + body);
                })
                .body(JdResponse.class);
    }
}
