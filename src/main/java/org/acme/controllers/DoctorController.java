package org.acme.controllers;




import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Doctor;
import org.acme.repositories.DoctorRepository;

import java.util.List;

@Path("/api/doctors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorController {

    @Inject
    DoctorRepository doctorRepository;

    @GET
    public List<Doctor> getAllDoctors() {
        return doctorRepository.listAll(); // Retrieve all doctors
    }

    @POST
    @Transactional
    public Response createDoctor(@Valid Doctor doctor) {
        doctorRepository.persist(doctor); // Persist the new doctor
        return Response.status(Response.Status.CREATED).entity(doctor).build(); // Return created doctor
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteDoctor(@PathParam("id") Long id) {
        if (doctorRepository.deleteById(id)) {
            return Response.noContent().build(); // Successfully deleted
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found with id: " + id).build(); // Doctor not found
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateDoctor(@PathParam("id") Long id, @Valid Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(id);
//        if (doctor == null) {
//            throw new ResourceNotFoundException("Doctor not found with id: " + id); // Throw custom exception if not found
//        }

        // Update doctor's details
        doctor.setName(doctorDetails.getName());
        doctor.setSpecialty(doctorDetails.getSpecialty());
        doctor.setPhone(doctorDetails.getPhone());
        doctor.setEmail(doctorDetails.getEmail());
        doctor.setStart_time(doctorDetails.getStart_time());
        doctor.setEnd_time(doctorDetails.getEnd_time());
        doctor.setExperience(doctorDetails.getExperience());
        doctor.setDp(doctorDetails.getDp());
        doctor.setLocation(doctorDetails.getLocation());

        doctorRepository.persist(doctor); // Persist the updated doctor
        return Response.ok(doctor).build(); // Return updated doctor
    }

    @GET
    @Path("/count")
    public Response getDoctorCount() {
        try {
            long count = doctorRepository.count(); // Count total doctors
            return Response.ok("{\"count\": " + count + "}").build(); // Return count in JSON format
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching doctor count: " + e.getMessage()).build(); // Handle exceptions
        }
    }
}

