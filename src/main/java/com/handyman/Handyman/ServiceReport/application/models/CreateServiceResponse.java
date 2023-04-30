package com.handyman.Handyman.ServiceReport.application.models;

import java.time.LocalDateTime;
import java.util.Date;

public class CreateServiceResponse {
    private String id;
    private String idTechnical;
    private String idService;
    private String startDate;
    private String endDate;

    public CreateServiceResponse() {
    }

    public CreateServiceResponse(String id, String idTechnical, String idService, String startDate, String endDate) {
        this.id = id;
        this.idTechnical = idTechnical;
        this.idService = idService;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTechnical() {
        return idTechnical;
    }

    public void setIdTechnical(String idTechnical) {
        this.idTechnical = idTechnical;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    //
//    private final ServiceDetail serviceDetail;
//
//    public CreateServiceResponse(ServiceDetail serviceDetail) {
//        this.serviceDetail = serviceDetail;
//    }
//
//
//    public ServiceDetail getService() {
//        return serviceDetail;
//    }

}
