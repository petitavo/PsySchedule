package com.nas.psyschedule.appointment.interfaces.rest.resources;

public record AppointmentResource(Long id, String date, String reason, String status, Long psychologistId, Long patientId) {

}
