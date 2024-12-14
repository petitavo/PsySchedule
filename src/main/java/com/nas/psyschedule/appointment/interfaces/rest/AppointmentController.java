package com.nas.psyschedule.appointment.interfaces.rest;

import com.nas.psyschedule.appointment.domain.model.aggregates.Appointment;
import com.nas.psyschedule.appointment.domain.model.commands.DeleteAppointmentCommand;
import com.nas.psyschedule.appointment.domain.model.queries.GetAppointmentsByPatientIdQuery;
import com.nas.psyschedule.appointment.domain.services.AppointmentCommandService;
import com.nas.psyschedule.appointment.domain.services.AppointmentQueryService;
import com.nas.psyschedule.appointment.interfaces.rest.resources.AppointmentResource;
import com.nas.psyschedule.appointment.interfaces.rest.resources.CreateAppointmentResource;
import com.nas.psyschedule.appointment.interfaces.rest.resources.UpdateAppointmentResource;
import com.nas.psyschedule.appointment.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.nas.psyschedule.appointment.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import com.nas.psyschedule.appointment.interfaces.rest.transform.UpdateAppointmentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;



@RestController
@RequestMapping(value = "/api/v1/appointments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Appointment", description = "Endpoints for managing appointments")
public class AppointmentController {
    private final AppointmentQueryService appointmentQueryService;
    private final AppointmentCommandService appointmentCommandService;

    public AppointmentController(AppointmentQueryService appointmentQueryService, AppointmentCommandService appointmentCommandService) {
        this.appointmentQueryService = appointmentQueryService;
        this.appointmentCommandService = appointmentCommandService;
    }

    /**
     * Create an appointment
     * @param resource CreateAppointmentResource
     * @return ResponseEntity<AppointmentResource>
     */
    @Operation(summary="Create an appointment", description="Create a new appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appointment created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Appointment already exists")
    })
    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource){
        Optional<Appointment> appointment = appointmentCommandService
                .handle(CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource));
        return appointment.map(a -> new ResponseEntity<>(AppointmentResourceFromEntityAssembler.toResourceFromEntity(a), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Update an appointment
     * @param appointmentId the appointment id
     * @param resource UpdateAppointmentResource
     * @return ResponseEntity<AppointmentResource>
     */
    @PutMapping("/{appointmentId}")
    @Operation(summary="Update an appointment", description="Update an existing appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    public ResponseEntity<AppointmentResource> updateAppointment(@PathVariable Long appointmentId, @RequestBody UpdateAppointmentResource resource){
        var updateAppointmentCommand = UpdateAppointmentCommandFromResourceAssembler.toCommandFromResource(appointmentId, resource);
        var updatedAppointment = appointmentCommandService.handle(updateAppointmentCommand);
        if(updatedAppointment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var updateAppointmentEntity = updatedAppointment.get();
        var updatedAppointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(updateAppointmentEntity);
        return ResponseEntity.ok(updatedAppointmentResource);
    }

    /**
     * Delete an appointment
     * @param appointmentId the appointment id
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{appointmentId}")
    @Operation(summary="Delete an appointment", description="Delete an existing appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Appointment deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId){
        var deleteAppointmentCommand = new DeleteAppointmentCommand(appointmentId);
        appointmentCommandService.handle(deleteAppointmentCommand);
        return ResponseEntity.ok("Appointment deleted");
    }

    /**
     * Get appointments by patientId
     * @param patientId the patient id
     * @return ResponseEntity<AppointmentResource>
     */
    @GetMapping("/patient/{patientId}")
    @Operation(summary="Get appointments by patientId", description="Get appointments by patientId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Appointments not found")
    })
    public ResponseEntity<AppointmentResource> getAppointmentByPatientId(@PathVariable Long patientId){
        List<Appointment> appointment = appointmentQueryService.handle(new GetAppointmentsByPatientIdQuery(patientId));
        if(appointment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get(0));
        return ResponseEntity.ok(appointmentResource);
    }


    /**
     * Get appointments by psychologistId
     * @param psychologistId the psychologist id
     * @return ResponseEntity<AppointmentResource>
     */
    @GetMapping("/psychologist/{psychologistId}")
    @Operation(summary="Get appointments by psychologistId", description="Get appointments by psychologistId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Appointments not found")
    })
    public ResponseEntity<AppointmentResource> getAppointmentByPsychologistId(@PathVariable Long psychologistId){
        List<Appointment> appointment = appointmentQueryService.handle(new GetAppointmentsByPatientIdQuery(psychologistId));
        if(appointment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get(0));
        return ResponseEntity.ok(appointmentResource);
    }

    /**
     * Get all appointments
     * @return ResponseEntity<List<AppointmentResource>>
     */
    @GetMapping
    @Operation(summary="Get all appointments", description="Get all appointments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Appointments not found")
    })
    public ResponseEntity<List<AppointmentResource>> getAllAppointments(){
        List<Appointment> appointments = appointmentQueryService.handle();
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }
}
