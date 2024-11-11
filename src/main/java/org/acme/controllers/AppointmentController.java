package org.acme.controllers;



import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Appointment;
import org.acme.repositories.AppointmentRepository;

import java.util.List;

@Path("/api/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentController {

    @Inject
    AppointmentRepository appointmentRepository;

    @GET
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.listAll(); // Retrieve all appointments
    }

    @POST
    @Transactional
    public Response createAppointment(@Valid Appointment appointment) {
        appointmentRepository.persist(appointment); // Persist the appointment directly
        return Response.status(Response.Status.CREATED).entity(appointment).build(); // Return created appointment
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteAppointment(@PathParam("id") Long id) {
        if (appointmentRepository.deleteById(id)) {
            return Response.noContent().build(); // Successfully deleted
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found with id: " + id).build(); // Appointment not found
        }
    }

    @GET
    @Path("/count")
    public Response getAppointmentCount() {
        try {
            long count = appointmentRepository.count(); // Count total appointments
            return Response.ok("{\"count\": " + count + "}").build(); // Return count in JSON format
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching appointment count: " + e.getMessage()).build(); // Handle exceptions
        }
    }
}

