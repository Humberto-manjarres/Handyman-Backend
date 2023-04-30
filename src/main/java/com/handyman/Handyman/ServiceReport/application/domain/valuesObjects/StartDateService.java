package com.handyman.Handyman.ServiceReport.application.domain.valuesObjects;

import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;
import java.util.Date;

public class StartDateService {
    private LocalDateTime startDate;

    private StartDateService(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public static StartDateService create(LocalDateTime value){
        Validate.notNull(value,"The Start date field cannot be empty or null.");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime start = LocalDateTime.parse(value, formatter);
        return new StartDateService(value);
    }

    public LocalDateTime getStartDate(){
        return startDate;
    }

}
