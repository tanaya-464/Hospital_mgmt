package org.acme.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.acme.entity.Doctor;
import org.acme.entity.Patient;

import java.time.LocalDate;

@Data
public class AppointmentDTO {
    private long id;

    private LocalDate appointmentDate;
    private String slot;
    private int cost;
    private String status;
    private PatientDTO patient;
    private DoctorDTO doctor;
}