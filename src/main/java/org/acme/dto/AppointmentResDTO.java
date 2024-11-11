package org.acme.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.acme.entity.Doctor;
import org.acme.entity.Patient;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentResDTO {
    private long app_id;
    private LocalDate appointmentDate;
    private String slot;
    private String status ;
    private PatientDTO patient;
    private DoctorDTO doctor;
}
