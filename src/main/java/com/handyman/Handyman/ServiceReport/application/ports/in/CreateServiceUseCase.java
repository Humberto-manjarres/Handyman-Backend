package com.handyman.Handyman.ServiceReport.application.ports.in;

import com.handyman.Handyman.ServiceReport.application.models.CreateServiceRequest;
import com.handyman.Handyman.ServiceReport.application.models.CreateServiceResponse;
import com.handyman.Handyman.commons.UseCase;
import reactor.core.publisher.Mono;

public interface CreateServiceUseCase extends UseCase<CreateServiceRequest, Mono<CreateServiceResponse>> {
}
