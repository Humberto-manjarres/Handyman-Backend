package com.handyman.Handyman.ServiceReport.application.services;

import com.handyman.Handyman.ServiceReport.application.models.CreateServiceRequest;
import com.handyman.Handyman.ServiceReport.application.models.CreateServiceResponse;
import com.handyman.Handyman.ServiceReport.application.ports.in.CreateServiceUseCase;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.time.Month;


@SpringBootTest
class CreateServicesTest {

    @Autowired
    private CreateServices createServices;

    @Test
    void execute() {
        CreateServiceResponse createServiceResponse = new CreateServiceResponse(
                "6332587e34acda4f8209221d",
                "73197854",
                "456",
                "2022-09-12 02:55:00",
                "2022-09-13 02:55:00"
        );

        LocalDateTime startDate = LocalDateTime.of(2022, Month.SEPTEMBER,12,02,55,00);
        LocalDateTime endDate = LocalDateTime.of(2022, Month.SEPTEMBER,13,02,55,00);
        //CreateServiceRequest createServiceRequest = new CreateServiceRequest("73197854","456", startDate, endDate);
        CreateServiceRequest createServiceRequest = new CreateServiceRequest("73197854","456", startDate, endDate);

        Mono<CreateServiceResponse> createServiceResponseMono = createServices.execute(createServiceRequest);

        /*después de guardar lo buscamos y comparamos*/
        StepVerifier.create(createServiceResponseMono).assertNext(r -> {
            assertThat(r).isNotNull();
            assertThat(r.getIdTechnical()).isEqualTo(createServiceResponse.getIdTechnical());
        }).verifyComplete();
    }
}