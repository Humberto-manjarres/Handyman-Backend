package com.handyman.Handyman.commons.mapper;

import com.handyman.Handyman.ServiceReport.application.domain.ServiceDetail;
import com.handyman.Handyman.ServiceReport.infrastructure.adapters.out.ServiceData;

import java.time.LocalDateTime;
import java.util.Date;

public class ServiceMapper {
    public static ServiceData toServiceData(ServiceDetail serviceDetail){
        String idTechnical = serviceDetail.getIdTechnical().getIdTechnical();
        String idService = serviceDetail.getIdService().getIdTechnical();
        String startDate = serviceDetail.getStartDateService().getStartDate().toString();
        String endDate = serviceDetail.getEndDateService().getEndDate().toString();
        String week = serviceDetail.getWeek();
        return new ServiceData(idTechnical,idService,startDate,endDate,week);
    }
}
