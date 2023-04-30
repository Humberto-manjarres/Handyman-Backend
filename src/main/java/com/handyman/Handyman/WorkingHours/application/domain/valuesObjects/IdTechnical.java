package com.handyman.Handyman.WorkingHours.application.domain.valuesObjects;

import org.apache.commons.lang3.Validate;

public class IdTechnical {
    private String idTechnical;

    private IdTechnical(String value) {
        this.idTechnical = value;
    }
    public static IdTechnical create(String value){
        Validate.notNull(value.toString(),"The id_technical field cannot be empty or null.");
        Validate.isTrue(value.toString().length() <= 10,"The maximum id_technical size is 10 characters.");
        return new IdTechnical(value);
    }

    public String getIdTechnical(){
        return idTechnical;
    }
}
