package com.handyman.Handyman.WorkingHours.application.ports.out;

import com.handyman.Handyman.WorkingHours.application.domain.WorkedHoursDetail;
import com.handyman.Handyman.WorkingHours.application.models.QueryServiceResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IWorkedHours {
    Mono<QueryServiceResponse> query(WorkedHoursDetail workedHoursDetail);
}
