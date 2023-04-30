package com.handyman.Handyman.WorkingHours.application.models;

public class QueryServiceResponse {
    private Integer regularHours;
    private Integer nightHours;
    private Integer sundayHours;
    private Integer regularHoursExtras;
    private Integer nightHoursExtras;
    private Integer sundayOvertime;

    public QueryServiceResponse(Integer regularHours, Integer nightHours, Integer sundayHours, Integer regularHoursExtras, Integer nightHoursExtras,Integer sundayOvertime) {
        this.regularHours = regularHours;
        this.nightHours = nightHours;
        this.sundayHours = sundayHours;
        this.regularHoursExtras = regularHoursExtras;
        this.nightHoursExtras = nightHoursExtras;
        this.sundayOvertime = sundayOvertime;
    }

    public QueryServiceResponse() {
    }

    public Integer getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(Integer regularHours) {
        this.regularHours = regularHours;
    }

    public Integer getNightHours() {
        return nightHours;
    }

    public void setNightHours(Integer nightHours) {
        this.nightHours = nightHours;
    }

    public Integer getSundayHours() {
        return sundayHours;
    }

    public void setSundayHours(Integer sundayHours) {
        this.sundayHours = sundayHours;
    }

    public Integer getRegularHoursExtras() {
        return regularHoursExtras;
    }

    public void setRegularHoursExtras(Integer regularHoursExtras) {
        this.regularHoursExtras = regularHoursExtras;
    }

    public Integer getNightHoursExtras() {
        return nightHoursExtras;
    }

    public void setNightHoursExtras(Integer nightHoursExtras) {
        this.nightHoursExtras = nightHoursExtras;
    }

    public Integer getSundayOvertime() {
        return sundayOvertime;
    }

    public void setSundayOvertime(Integer sundayOvertime) {
        this.sundayOvertime = sundayOvertime;
    }
}
