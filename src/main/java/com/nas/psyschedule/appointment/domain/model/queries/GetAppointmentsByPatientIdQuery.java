package com.nas.psyschedule.appointment.domain.model.queries;

public record GetAppointmentsByPatientIdQuery(Long patientId) {
    public GetAppointmentsByPatientIdQuery {
        if (patientId == null) {
            throw new IllegalArgumentException("PatientId cannot be null");
        }
    }
}
