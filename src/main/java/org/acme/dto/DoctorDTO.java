package org.acme.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDTO {

    private long id;
    private String name;
    private long phone;
    private String email;
    private String password;
    private LocalTime start_time;
    private LocalTime end_time;
    private String specialty;
    private int experience;
    private byte[] dp;
    private String location;

}