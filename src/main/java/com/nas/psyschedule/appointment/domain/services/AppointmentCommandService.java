package com.nas.psyschedule.appointment.domain.services;

import com.nas.psyschedule.appointment.domain.model.aggregates.Appointment;
import com.nas.psyschedule.appointment.domain.model.commands.CreateAppointmentCommand;
import com.nas.psyschedule.appointment.domain.model.commands.DeleteAppointmentCommand;
import com.nas.psyschedule.appointment.domain.model.commands.UpdateAppointmentCommand;

import java.util.Optional;

public interface AppointmentCommandService {

    Optional<Appointment> handle (CreateAppointmentCommand command);

    Optional<Appointment> handle (UpdateAppointmentCommand command);

    void handle (DeleteAppointmentCommand command);


}
