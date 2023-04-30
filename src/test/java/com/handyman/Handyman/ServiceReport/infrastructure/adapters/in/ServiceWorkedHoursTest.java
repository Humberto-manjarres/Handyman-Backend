package com.handyman.Handyman.ServiceReport.infrastructure.adapters.in;

import com.handyman.Handyman.ServiceReport.application.models.CreateServiceRequest;
import com.handyman.Handyman.ServiceReport.application.ports.in.CreateServiceUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServiceWorkedHoursTest {

    @Autowired
    private WebTestClient client;

    @Test
    void execute() {
        LocalDateTime startDate = LocalDateTime.of(2022, Month.SEPTEMBER,12,02,55,00);
        LocalDateTime endDate = LocalDateTime.of(2022, Month.SEPTEMBER,13,02,55,00);
        //CreateServiceRequest createServiceRequest = new CreateServiceRequest("73197854","456", startDate, endDate);
        CreateServiceRequest createServiceRequest = new CreateServiceRequest("73197854","456", startDate, endDate);
        client.post().uri("/api/service")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(createServiceRequest), CreateServiceRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.idTechnical").isNotEmpty()
                .jsonPath("$.idService").isNotEmpty()
                .jsonPath("$.idService").isEqualTo("456")
                .jsonPath("$.startDate").isEqualTo("2022-09-12 02:55:00");
    }

}