package com.handyman.Handyman.ServiceReport.application.services;

import java.time.LocalDateTime;

@FunctionalInterface
public interface ValidateDate {
    boolean majorOrMinorRangeDate(LocalDateTime start, LocalDateTime end);
}
