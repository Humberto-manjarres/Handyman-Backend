package com.handyman.Handyman.ServiceReport.application.domain;

import com.handyman.Handyman.ServiceReport.application.domain.valuesObjects.EndDateService;
import com.handyman.Handyman.ServiceReport.application.domain.valuesObjects.IdService;
import com.handyman.Handyman.ServiceReport.application.domain.valuesObjects.IdTechnical;
import com.handyman.Handyman.ServiceReport.application.domain.valuesObjects.StartDateService;

public class ServiceDetail {
    private IdTechnical idTechnical;
    private IdService idService;
    private StartDateService startDateService;
    private EndDateService endDateService;
    private String week;

    public IdTechnical getIdTechnical() {
        return idTechnical;
    }

    public void setIdTechnical(IdTechnical idTechnical) {
        this.idTechnical = idTechnical;
    }

    public IdService getIdService() {
        return idService;
    }

    public void setIdService(IdService idService) {
        this.idService = idService;
    }

    public StartDateService getStartDateService() {
        return startDateService;
    }

    public void setStartDateService(StartDateService startDateService) {
        this.startDateService = startDateService;
    }

    public EndDateService getEndDateService() {
        return endDateService;
    }

    public void setEndDateService(EndDateService endDateService) {
        this.endDateService = endDateService;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
