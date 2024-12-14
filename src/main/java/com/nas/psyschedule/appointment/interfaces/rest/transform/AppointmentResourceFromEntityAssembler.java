package com.nas.psyschedule.appointment.interfaces.rest.transform;

import com.nas.psyschedule.appointment.domain.model.aggregates.Appointment;
import com.nas.psyschedule.appointment.interfaces.rest.resources.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment entity){
        return new AppointmentResource(
                entity.getId(),
                entity.getDate(),
                entity.getReason(),
                entity.getStatus(),
                entity.getPsychologistId(),
                entity.getPatientId()
        );
    }
}
