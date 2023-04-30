package com.handyman.Handyman.ServiceReport.application.domain.valuesObjects;

import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;
import java.util.Date;

public class EndDateService {
    private LocalDateTime endDate;

    private EndDateService(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public static EndDateService create(LocalDateTime value){
        Validate.notNull(value,"The end date field cannot be empty or null.");
        return new EndDateService(value);
    }

    public LocalDateTime getEndDate(){
        return endDate;
    }
}
