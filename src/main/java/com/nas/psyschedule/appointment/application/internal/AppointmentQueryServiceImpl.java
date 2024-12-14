package com.nas.psyschedule.appointment.application.internal;

import com.nas.psyschedule.appointment.domain.model.aggregates.Appointment;
import com.nas.psyschedule.appointment.domain.model.queries.GetAppointmentsByPatientIdQuery;
import com.nas.psyschedule.appointment.domain.model.queries.GetAppointmentsByPsychologistIdQuery;
import com.nas.psyschedule.appointment.domain.services.AppointmentQueryService;
import com.nas.psyschedule.appointment.infrastructure.persistence.jpa.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> handle(GetAppointmentsByPsychologistIdQuery query) {
        return appointmentRepository.findByPsychologistId(query.psychologistId());
    }

    @Override
    public List<Appointment> handle(GetAppointmentsByPatientIdQuery query) {
        return appointmentRepository.findByPatientId(query.patientId());
    }

    @Override
    public List<Appointment> handle() {
        return appointmentRepository.findAll();
    }

}
