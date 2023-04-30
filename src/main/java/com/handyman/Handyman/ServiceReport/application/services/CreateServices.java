package com.handyman.Handyman.ServiceReport.application.services;

import com.handyman.Handyman.ServiceReport.application.domain.ServiceDetail;
import com.handyman.Handyman.ServiceReport.application.domain.WeekNumber;
import com.handyman.Handyman.ServiceReport.application.domain.valuesObjects.EndDateService;
import com.handyman.Handyman.ServiceReport.application.domain.valuesObjects.IdService;
import com.handyman.Handyman.ServiceReport.application.domain.valuesObjects.IdTechnical;
import com.handyman.Handyman.ServiceReport.application.domain.valuesObjects.StartDateService;
import com.handyman.Handyman.ServiceReport.application.models.CreateServiceRequest;
import com.handyman.Handyman.ServiceReport.application.models.CreateServiceResponse;
import com.handyman.Handyman.ServiceReport.application.ports.in.CreateServiceUseCase;
import com.handyman.Handyman.ServiceReport.application.ports.out.IServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class CreateServices implements CreateServiceUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateServices.class);

    private final IServices repository;

    public CreateServices(IServices repository) {
        this.repository = repository;
    }

    @Override
    public Mono<CreateServiceResponse> execute(CreateServiceRequest createServiceRequest) {
        ServiceDetail serviceDetail = new ServiceDetail();
        serviceDetail.setIdTechnical(IdTechnical.create(createServiceRequest.getIdTechnical()));
        serviceDetail.setIdService(IdService.create(createServiceRequest.getIdService()));

        serviceDetail.setStartDateService(StartDateService.create(createServiceRequest.getStartDate()));
        serviceDetail.setEndDateService(EndDateService.create(createServiceRequest.getEndDate()));
        if (validateDateMajorOnMinor(createServiceRequest.getStartDate(), createServiceRequest.getEndDate())){
            throw new RuntimeException("start date cannot be after end date");
        }
        try {
           serviceDetail.setWeek(changeToWeek(createServiceRequest.getStartDate(),createServiceRequest.getEndDate()));
        } catch (ParseException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        /*buscar por idService si ya existe realizado o se está realizando, de ser así debe retornar un error*/
        Mono<CreateServiceResponse> byIdService = repository.findByIdService(Mono.just(createServiceRequest.getIdService()));

        return repository.saveService(serviceDetail).flatMap(r ->{
            CreateServiceResponse response = new CreateServiceResponse();
            response.setId(r.getId());
            response.setIdTechnical(r.getIdTechnical());
            response.setIdService(r.getIdService());
            response.setStartDate(r.getStartDate());
            response.setEndDate(r.getEndDate());
            return Mono.just(response);
        });
    }

    private String changeToWeek(LocalDateTime starDate, LocalDateTime endDate) throws ParseException {
        String weekDto = "";
        for (WeekNumber week : WeekNumber.values()) {
            if (starDate.compareTo(toLocalDateTime(week.getStartDate())) >=0 && toLocalDateTime(week.getEndDate()).compareTo(endDate) >=0){
                weekDto = week.getName();
            }
        }
        return weekDto;
    }

    public LocalDateTime toLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }

    public boolean validateDateMajorOnMinor(LocalDateTime start, LocalDateTime end){
        ValidateDate validateDate = (startDate,endDate) -> start.isAfter(end);
        return validateDate.majorOrMinorRangeDate(start,end);
    }
}
