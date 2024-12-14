package com.nas.psyschedule.appointment.interfaces.rest.transform;

import com.nas.psyschedule.appointment.domain.model.commands.UpdateAppointmentCommand;
import com.nas.psyschedule.appointment.interfaces.rest.resources.UpdateAppointmentResource;

public class UpdateAppointmentCommandFromResourceAssembler {
    public static UpdateAppointmentCommand toCommandFromResource(Long appointmentId, UpdateAppointmentResource resource) {
        return new UpdateAppointmentCommand(
              appointmentId,
                resource.date(),
                resource.reason(),
                resource.status()
        );
    }
}
