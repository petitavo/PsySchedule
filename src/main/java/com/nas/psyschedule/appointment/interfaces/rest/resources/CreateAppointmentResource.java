package com.nas.psyschedule.appointment.interfaces.rest.resources;

/**
 *  this.date = command.date();
 *         this.reason = command.reason();
 *         this.status = command.status();
 *         this.psychologistId = command.psychologistId();
 *         this.patientId = command.patientId();
 */
public record CreateAppointmentResource(String date, String reason, String status, Long psychologistId, Long patientId) {
    public CreateAppointmentResource {
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
