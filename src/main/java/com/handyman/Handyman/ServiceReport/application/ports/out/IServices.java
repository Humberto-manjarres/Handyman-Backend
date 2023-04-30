package com.handyman.Handyman.ServiceReport.application.ports.out;

import com.handyman.Handyman.ServiceReport.application.domain.ServiceDetail;
import com.handyman.Handyman.ServiceReport.application.models.CreateServiceResponse;
import reactor.core.publisher.Mono;

public interface IServices {
    Mono<CreateServiceResponse> saveService(ServiceDetail serviceDetail);
    Mono<CreateServiceResponse> findByIdService(Mono<String> idService);
}
