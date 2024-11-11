package org.acme.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.acme.entity.Appointment;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO {
    private long id;
    private String name;
    private LocalDate dob;
    private String gender;
    private long phone;
    private String email;
    private String password;
//    private List<Appointment> appointments;

}