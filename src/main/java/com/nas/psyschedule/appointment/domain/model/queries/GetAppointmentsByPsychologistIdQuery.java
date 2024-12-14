package com.nas.psyschedule.appointment.domain.model.queries;

public record GetAppointmentsByPsychologistIdQuery(Long psychologistId) {
    public GetAppointmentsByPsychologistIdQuery {
        if (psychologistId == null) {
            throw new IllegalArgumentException("PsychologistId cannot be null");
        }
    }
}
