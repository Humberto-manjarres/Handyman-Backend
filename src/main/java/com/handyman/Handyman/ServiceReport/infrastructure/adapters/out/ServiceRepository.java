package com.handyman.Handyman.ServiceReport.infrastructure.adapters.out;

import com.handyman.Handyman.ServiceReport.application.domain.ServiceDetail;
import com.handyman.Handyman.ServiceReport.application.models.CreateServiceResponse;
import com.handyman.Handyman.ServiceReport.application.ports.out.IServices;
import com.handyman.Handyman.commons.mapper.ServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Service
public class ServiceRepository implements IServices {
    private static final Logger log = LoggerFactory.getLogger(ServiceRepository.class);

    private final MongoServiceRepository mongoServiceRepository;

    public ServiceRepository(MongoServiceRepository mongoServiceRepository) {
        this.mongoServiceRepository = mongoServiceRepository;
    }

    @Override
    public Mono<CreateServiceResponse> saveService(ServiceDetail serviceDetail) {
        ServiceData data = ServiceMapper.toServiceData(serviceDetail);
        data.setStartDate(convertToLocalDateTimeViaInstant2(serviceDetail.getStartDateService().getStartDate()));
        data.setEndDate(convertToLocalDateTimeViaInstant2(serviceDetail.getEndDateService().getEndDate()));
        return mongoServiceRepository.save(data).flatMap(r-> {
            final CreateServiceResponse response = new CreateServiceResponse();
            response.setId(r.getId());
            response.setIdTechnical(r.getIdTechnical());
            response.setIdService(r.getIdService());
            response.setStartDate(r.getStartDate());
            response.setEndDate(r.getEndDate());
            return Mono.just(response);
        });

    }

    @Override
    public Mono<CreateServiceResponse> findByIdService(Mono<String> idService) {
        String service = idService.block();
        return mongoServiceRepository.findServices(service).flatMap(s->{
            CreateServiceResponse response = new CreateServiceResponse();
            response.setId(s.getId());
            return Mono.just(response);
        });
//        Mono<ServiceData> byId = mongoServiceRepository.findServices("456");
//        byId
//                .subscribe(e -> System.out.println(e.getIdService()));
//        CreateServiceResponse response = new CreateServiceResponse();
//        return Mono.just(response);
    }

    public String convertToLocalDateTimeViaInstant2(LocalDateTime dateToConvert){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = dateToConvert
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return localDateTime.format(formatter);
    }
}
