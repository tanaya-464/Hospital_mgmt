package org.acme.controllers;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.LoginDTO;
import org.acme.entity.Patient;
import org.acme.service.DoctorService;
import org.acme.service.PatientService;
import org.jboss.logging.annotations.Pos;

@Path("/api/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    PatientService patientService;

    @Inject
    DoctorService doctorService;


    @POST
    @Transactional
    public String validateLogin(LoginDTO loginDTO){
        String res="";
        if(loginDTO.getIsDoctor()){
            res= doctorService.validate(loginDTO)?"Login Successful doctor":"Invalid Credentials doctor";
        }
        else{
            res= patientService.validate(loginDTO)?"Login Successful patient":"Invalid Credentials patient";
        }
        return  res;

    }
}
