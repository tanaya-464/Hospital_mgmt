package org.acme.dto;

import java.util.List;

public class ApiResponseDTO {

    private String token;
    private String role;
    private String expirationTime;


    private DoctorDTO doctor;
    private PatientDTO patient;
    private AppointmentDTO appointment;
    private List<DoctorDTO> doctorList;
    private List<PatientDTO> patientList;
    private List<AppointmentDTO> appointmentList;
}
