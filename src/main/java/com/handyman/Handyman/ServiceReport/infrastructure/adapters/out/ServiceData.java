package com.handyman.Handyman.ServiceReport.infrastructure.adapters.out;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "services")
public class ServiceData {
    @Id
    private String id;

    private String idTechnical;
    private String idService;
    private String startDate;
    private String endDate;
    private String week;

    public ServiceData(String idTechnical, String idService, String startDate, String endDate, String week) {
        this.idTechnical = idTechnical;
        this.idService = idService;
        this.startDate = startDate;
        this.endDate = endDate;
        this.week = week;
    }

    public ServiceData() {
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

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "ServiceData{" +
                "id='" + id + '\'' +
                ", idTechnical='" + idTechnical + '\'' +
                ", idService='" + idService + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
