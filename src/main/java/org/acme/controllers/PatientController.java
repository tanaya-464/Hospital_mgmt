
package org.acme.controllers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.PatientDTO;
import org.acme.service.PatientService;
import org.jboss.logging.annotations.Pos;

import java.util.List;

@Path("/patients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PatientController {

    @Inject
    PatientService patientService;

    @GET
    @Path("/{id}")
    public Response getPatientById(@PathParam("id") long id) {
        PatientDTO patientDTO = patientService.getPatientById(id);
        return Response.ok(patientDTO).build();
    }

    @GET
    public Response getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return Response.ok(patients).build();
    }

    @POST
    @Transactional
    public Response savePatient(PatientDTO patientDTO) {
        patientService.savePatient(patientDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePatient(@PathParam("id") long id, PatientDTO patientDTO) {
        patientService.updatePatient(id, patientDTO);
        return Response.ok(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePatient(@PathParam("id") long id) {
        patientService.deletePatient(id);
        return Response.noContent().build();
    }
}