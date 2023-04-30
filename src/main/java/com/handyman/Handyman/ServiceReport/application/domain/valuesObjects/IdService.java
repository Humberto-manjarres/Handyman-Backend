package com.handyman.Handyman.ServiceReport.application.domain.valuesObjects;

import org.apache.commons.lang3.Validate;

public class IdService {
    private String idService;

    private IdService(String idService) {
        this.idService = idService;
    }

    public static IdService create(String value){
        Validate.notNull(value.toString(),"The id_service field cannot be empty or null.");
        return new IdService(value);
    }

    public String getIdTechnical(){
        return idService;
    }
}
