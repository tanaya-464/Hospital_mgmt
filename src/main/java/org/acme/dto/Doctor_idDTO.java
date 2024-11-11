package org.acme.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doctor_idDTO {
    private long id;
    private long doctor_id;
}