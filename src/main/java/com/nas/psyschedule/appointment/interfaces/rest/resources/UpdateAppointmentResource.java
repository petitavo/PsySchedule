package com.nas.psyschedule.appointment.interfaces.rest.resources;

public record UpdateAppointmentResource(String date, String reason, String status) {
    public UpdateAppointmentResource {
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("Reason cannot be null or empty");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
