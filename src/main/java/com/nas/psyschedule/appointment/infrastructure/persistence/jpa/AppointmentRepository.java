package com.nas.psyschedule.appointment.infrastructure.persistence.jpa;

import com.nas.psyschedule.appointment.domain.model.aggregates.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    boolean existsByDateAndPsychologistId(String date, Long psychologistId);

    boolean existsByPatientId(Long patientId);

    List<Appointment> findByPsychologistId(Long psychologistId);

    List<Appointment> findByPatientId(Long patientId);
}
