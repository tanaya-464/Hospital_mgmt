package org.acme.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;
    private Boolean isDoctor;

}
