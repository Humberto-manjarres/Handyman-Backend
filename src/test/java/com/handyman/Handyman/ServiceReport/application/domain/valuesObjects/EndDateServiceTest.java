package com.handyman.Handyman.ServiceReport.application.domain.valuesObjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EndDateServiceTest {

    @Test
    void create() {
        LocalDateTime endDate = LocalDateTime.of(2022, Month.SEPTEMBER,12,02,55,00);
        EndDateService endDateService = EndDateService.create(endDate);
        assertNotNull(endDateService);
        assertEquals(endDate, endDateService.getEndDate());
    }

    @Test
    void createFail() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            EndDateService.create(null);
        });
        String currentMessage = exception.getMessage();
        String expectedMessage = "The end date field cannot be empty or null.";
        assertEquals(expectedMessage, currentMessage);
        assertEquals(NullPointerException.class, exception.getClass());
    }

    @Test
    void getEndDate() {
        LocalDateTime endDate = LocalDateTime.of(2022, Month.SEPTEMBER,12,02,55,00);
        EndDateService endDateService = EndDateService.create(endDate);
        assertNotNull(endDateService.getEndDate());
        assertTrue(endDateService.getEndDate() != null);
        assertFalse(endDateService.getEndDate() == null);
    }
}