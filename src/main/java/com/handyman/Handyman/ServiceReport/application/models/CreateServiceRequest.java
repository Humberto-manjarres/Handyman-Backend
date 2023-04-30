package com.handyman.Handyman.ServiceReport.application.models;

import java.time.LocalDateTime;


public class CreateServiceRequest {
    private String idTechnical;
    private String idService;
//    private String startDate;
//    private String endDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public CreateServiceRequest() {
    }

    public CreateServiceRequest(String idTechnical, String idService, LocalDateTime startDate, LocalDateTime endDate) {
        this.idTechnical = idTechnical;
        this.idService = idService;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /*
    public CreateServiceRequest(String idTechnical, String idService, String startDate, String endDate) {
        this.idTechnical = idTechnical;
        this.idService = idService;
        this.startDate = startDate;
        this.endDate = endDate;
    }*/

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
