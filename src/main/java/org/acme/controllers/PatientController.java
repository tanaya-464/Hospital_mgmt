package org.acme.controllers;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Patient;
import org.acme.repositories.PatientRepository;

import java.util.List;

@Path("/api/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientController {

    @Inject
    PatientRepository patientRepository;

    @GET
    public List<Patient> getAllPatients() {
        return patientRepository.listAll(); // Retrieve all patients
    }

    @POST
    @Transactional
    public Response createPatient(@Valid Patient patient) {
        // Hash the password before saving (consider using a utility method)
        patient.setPassword(hashPassword(patient.getPassword())); // Ensure password is hashed
        patientRepository.persist(patient); // Persist the new patient
        return Response.status(Response.Status.CREATED).entity(patient).build(); // Return created patient
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePatient(@PathParam("id") Long id) {
        if (patientRepository.deleteById(id)) {
            return Response.noContent().build(); // Successfully deleted
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Patient not found
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePatient(@PathParam("id") Long id, @Valid Patient patientDetails) {
        Patient patient = patientRepository.findById(id);
        if (patient == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found with id: " + id).build();
        }

       
        patient.setName(patientDetails.getName());
        patient.setDob(patientDetails.getDob()); // Update date of birth
        patient.setGender(patientDetails.getGender());
        patient.setPhone(patientDetails.getPhone());
        patient.setEmail(patientDetails.getEmail());


        if (patientDetails.getPassword() != null && !patientDetails.getPassword().isEmpty()) {
            patient.setPassword(hashPassword(patientDetails.getPassword())); // Hash the new password
        }

        patientRepository.persist(patient);
        return Response.ok(patient).build();
    }

    @GET
    @Path("/count")
    public Response getPatientCount() {
        try {
            long count = patientRepository.count(); // Count total patients
            return Response.ok("{\"count\": " + count + "}").build(); // Return count in JSON format
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching patient count: " + e.getMessage()).build(); // Handle exceptions
        }
    }


    private String hashPassword(String password) {

        return password;
    }
}
