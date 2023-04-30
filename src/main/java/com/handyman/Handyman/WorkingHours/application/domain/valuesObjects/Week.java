package com.handyman.Handyman.WorkingHours.application.domain.valuesObjects;

import org.apache.commons.lang3.Validate;

public class Week {
    private String week;

    private Week(String week) {
        this.week = week;
    }
    public static Week create(String value){
        Validate.notNull(value.toString(),"The week field cannot be empty or null.");
        return new Week(value);
    }
    public String getWeek(){
        return week;
    }

}
