package com.nas.psyschedule.appointment.domain.model.aggregates;

import com.nas.psyschedule.appointment.domain.model.commands.CreateAppointmentCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Appointment extends AbstractAggregateRoot<Appointment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long psychologistId;

    @Column(nullable = false)
    private Long patientId;

    protected Appointment() {}

    public Appointment(CreateAppointmentCommand command) {
        this.date = command.date();
        this.reason = command.reason();
        this.status = command.status();
        this.psychologistId = command.psychologistId();
        this.patientId = command.patientId();
    }

    public Appointment updateAppointment(String date, String reason, String status) {
        this.date = date;
        this.reason = reason;
        this.status = status;
        return this;
    }

}
