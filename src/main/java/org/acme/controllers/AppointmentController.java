package org.acme.controllers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.AppointmentDTO;
import org.acme.dto.AppointmentResDTO;
import org.acme.entity.Appointment;
import org.acme.service.AppointmentService;

import java.util.List;

@Path("/api/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentController {

    @Inject
    AppointmentService appointmentService; // Injecting AppointmentService

    @GET
    public Response getAllAppointments() {
        List<AppointmentResDTO> appointments = appointmentService.getAllAppointments(); // Retrieve all appointments
        return Response.ok(appointments).build(); // Return list of appointments
    }

    @GET
    @Path("/{id}")
    public Response getAppointmentById(@PathParam("id") Long id) {
        AppointmentDTO appointmentDTO = appointmentService.getAppointmentById(id); // Retrieve appointment by ID
        return Response.ok(appointmentDTO).build(); // Return the appointment
    }

@GET
@Path("/slots")
public Response getSlots(
        @QueryParam("doctorId") Long doctorId,
        @QueryParam("date") String date) {
    List<String> slots = appointmentService.getSlots(doctorId,date); // Retrieve all appointments
    return Response.ok(slots).build(); // Return list of appointments
}


    @POST
    @Transactional
    public Response createAppointment(@Valid AppointmentDTO appointmentDTO) {
        appointmentService.saveAppointment(appointmentDTO); // Persist the new appointment
        return Response.status(Response.Status.CREATED).entity(appointmentDTO).build(); // Return created appointment
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateAppointment(@PathParam("id") Long id, @Valid AppointmentDTO appointmentDTO) {
        appointmentService.updateAppointment(id, appointmentDTO); // Update appointment details
        return Response.ok(appointmentDTO).build(); // Return updated appointment
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteAppointment(@PathParam("id") Long id) {
        appointmentService.deleteAppointment(id); // Delete appointment by ID
        return Response.noContent().build(); // Successfully deleted
    }

    @GET
    @Path("/count")
    public Response getAppointmentCount() {
        long count = appointmentService.getAppointmentCount(); // Count total appointments
        return Response.ok("{\"count\": " + count + "}").build(); // Return count in JSON format
    }
}


//package org.acme.controllers;
//
//
//
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import jakarta.validation.Valid;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import org.acme.entity.Appointment;
//import org.acme.repositories.AppointmentRepository;
//
//import java.util.List;
//
//@Path("/api/appointments")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class AppointmentController {
//
//    @Inject
//    AppointmentRepository appointmentRepository;
//
//    @GET
//    public List<Appointment> getAllAppointments() {
//        return appointmentRepository.listAll(); // Retrieve all appointments
//    }
//
//    @POST
//    @Transactional
//    public Response createAppointment(@Valid Appointment appointment) {
//        appointmentRepository.persist(appointment); // Persist the appointment directly
//        return Response.status(Response.Status.CREATED).entity(appointment).build(); // Return created appointment
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Transactional
//    public Response deleteAppointment(@PathParam("id") Long id) {
//        if (appointmentRepository.deleteById(id)) {
//            return Response.noContent().build(); // Successfully deleted
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found with id: " + id).build(); // Appointment not found
//        }
//    }
//
//    @GET
//    @Path("/count")
//    public Response getAppointmentCount() {
//        try {
//            long count = appointmentRepository.count(); // Count total appointments
//            return Response.ok("{\"count\": " + count + "}").build(); // Return count in JSON format
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity("Error fetching appointment count: " + e.getMessage()).build(); // Handle exceptions
//        }
//    }
//}
//
