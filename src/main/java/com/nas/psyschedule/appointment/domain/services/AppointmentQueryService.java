package com.nas.psyschedule.appointment.domain.services;

import com.nas.psyschedule.appointment.domain.model.aggregates.Appointment;
import com.nas.psyschedule.appointment.domain.model.queries.GetAppointmentsByPatientIdQuery;
import com.nas.psyschedule.appointment.domain.model.queries.GetAppointmentsByPsychologistIdQuery;

import java.util.List;

public interface AppointmentQueryService {

    List<Appointment> handle(GetAppointmentsByPsychologistIdQuery query);

    List<Appointment> handle(GetAppointmentsByPatientIdQuery query);

    List<Appointment> handle();
}
