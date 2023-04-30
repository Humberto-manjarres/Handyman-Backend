package com.handyman.Handyman.WorkingHours.application.ports.in;

import com.handyman.Handyman.WorkingHours.application.models.QueryServiceRequest;
import com.handyman.Handyman.WorkingHours.application.models.QueryServiceResponse;
import com.handyman.Handyman.commons.UseCase;
import reactor.core.publisher.Mono;

public interface QueryWorkedHoursUseCase extends UseCase<QueryServiceRequest, Mono<QueryServiceResponse>> {
}
