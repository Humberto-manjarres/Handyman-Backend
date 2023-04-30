package com.handyman.Handyman.WorkingHours.application.models;

public class QueryServiceRequest {
    private String idTechnical;
    private String week;

    public QueryServiceRequest() {
    }

    public QueryServiceRequest(String idTechnical, String week) {
        this.idTechnical = idTechnical;
        this.week = week;
    }

    public String getIdTechnical() {
        return idTechnical;
    }

    public void setIdTechnical(String idTechnical) {
        this.idTechnical = idTechnical;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
