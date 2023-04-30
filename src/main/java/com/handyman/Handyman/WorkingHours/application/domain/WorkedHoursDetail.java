package com.handyman.Handyman.WorkingHours.application.domain;

import com.handyman.Handyman.WorkingHours.application.domain.valuesObjects.IdTechnical;
import com.handyman.Handyman.WorkingHours.application.domain.valuesObjects.Week;

public class WorkedHoursDetail {
    private IdTechnical idTechnical;
    private Week week;

    public IdTechnical getIdTechnical() {
        return idTechnical;
    }

    public void setIdTechnical(IdTechnical idTechnical) {
        this.idTechnical = idTechnical;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }
}
