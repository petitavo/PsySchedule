package com.nas.psyschedule.appointment.domain.model.commands;

public record UpdateAppointmentCommand(Long AppointmentId, String date, String reason, String status) {

    public UpdateAppointmentCommand {
        if (AppointmentId == null || AppointmentId <= 0) {
            throw new IllegalArgumentException("Appointment id cannot be null or less than 1");
        }
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
