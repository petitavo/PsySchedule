package com.nas.psyschedule.appointment.application.internal;

import com.nas.psyschedule.appointment.domain.model.aggregates.Appointment;
import com.nas.psyschedule.appointment.domain.model.commands.CreateAppointmentCommand;
import com.nas.psyschedule.appointment.domain.model.commands.DeleteAppointmentCommand;
import com.nas.psyschedule.appointment.domain.model.commands.UpdateAppointmentCommand;
import com.nas.psyschedule.appointment.domain.services.AppointmentCommandService;
import com.nas.psyschedule.appointment.infrastructure.persistence.jpa.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> handle(CreateAppointmentCommand command) {
       if (appointmentRepository.existsByDateAndPsychologistId(command.date(), command.psychologistId())) {
            throw new IllegalArgumentException("Appointment with date %s and psychologist id %s already exists".formatted(command.date(), command.psychologistId()));
        }
        if (appointmentRepository.existsByPatientId(command.patientId())) {
            throw new IllegalArgumentException("Appointment with patient id %s already exists".formatted(command.patientId()));
        }
        Appointment appointment = new Appointment(command);
        appointmentRepository.save(appointment);
        return Optional.of(appointment);
    }

    @Override
    public Optional<Appointment> handle(UpdateAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.AppointmentId())) {
            throw new IllegalArgumentException("Appointment with id %s not found".formatted(command.AppointmentId()));
        }
        var result = appointmentRepository.findById(command.AppointmentId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Appointment with id %s not found".formatted(command.AppointmentId()));
        }
        var appointmentToUpdate = result.get();
        try {
            var updatedAppointment = appointmentRepository.save(appointmentToUpdate.updateAppointment(
                    command.date(), command.reason(), command.status()));
            return Optional.of(updatedAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating appointment: %s".formatted(e.getMessage()));
        }
    }


    @Override
    public void handle(DeleteAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.appointmentId())) {
            throw new IllegalArgumentException("Appointment with id %s not found".formatted(command.appointmentId()));
        }
        try {
            appointmentRepository.deleteById(command.appointmentId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting appointment: %s".formatted(e.getMessage()));
        }

    }



}
