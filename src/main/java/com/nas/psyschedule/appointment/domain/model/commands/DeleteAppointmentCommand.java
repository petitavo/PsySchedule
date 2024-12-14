package com.nas.psyschedule.appointment.domain.model.commands;

public record DeleteAppointmentCommand(Long appointmentId) {

    public DeleteAppointmentCommand {
        if (appointmentId == null || appointmentId <= 0) {
            throw new IllegalArgumentException("Appointment id cannot be null or less than 1");
        }
    }
}
