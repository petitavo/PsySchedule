package com.nas.psyschedule.appointment.domain.model.commands;

public record CreateAppointmentCommand(
        String date,
        String reason,
        String status,
        Long psychologistId,
        Long patientId
) {
    public CreateAppointmentCommand {
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("Reason cannot be null or empty");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        if (psychologistId == null) {
            throw new IllegalArgumentException("PsychologistId cannot be null");
        }
        if (patientId == null) {
            throw new IllegalArgumentException("PatientId cannot be null");
        }
    }
}
