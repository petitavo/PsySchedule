package com.nas.psyschedule.appointment.interfaces.rest.transform;

import com.nas.psyschedule.appointment.domain.model.commands.CreateAppointmentCommand;
import com.nas.psyschedule.appointment.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {

    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(resource.date(), resource.reason(), resource.status(), resource.psychologistId(), resource.patientId());
    }
}

