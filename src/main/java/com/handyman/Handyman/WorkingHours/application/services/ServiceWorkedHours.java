package com.handyman.Handyman.WorkingHours.application.services;

import com.handyman.Handyman.WorkingHours.application.domain.WorkedHoursDetail;
import com.handyman.Handyman.WorkingHours.application.domain.valuesObjects.IdTechnical;
import com.handyman.Handyman.WorkingHours.application.domain.valuesObjects.Week;
import com.handyman.Handyman.WorkingHours.application.models.QueryServiceRequest;
import com.handyman.Handyman.WorkingHours.application.models.QueryServiceResponse;
import com.handyman.Handyman.WorkingHours.application.ports.in.QueryWorkedHoursUseCase;
import com.handyman.Handyman.WorkingHours.application.ports.out.IWorkedHours;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ServiceWorkedHours implements QueryWorkedHoursUseCase {
    private final IWorkedHours iWorkedHours;

    public ServiceWorkedHours(IWorkedHours iWorkedHours) {
        this.iWorkedHours = iWorkedHours;
    }

    @Override
    public Mono<QueryServiceResponse> execute(QueryServiceRequest queryServiceRequest) {
        WorkedHoursDetail detail = new WorkedHoursDetail();
        detail.setIdTechnical(IdTechnical.create(queryServiceRequest.getIdTechnical()));
        detail.setWeek(Week.create(queryServiceRequest.getWeek()));
        return iWorkedHours.query(detail);
    }
}
