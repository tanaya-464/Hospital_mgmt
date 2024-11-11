package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.AppointmentDTO;
import org.acme.dto.AppointmentResDTO;
import org.acme.entity.Appointment;
import org.acme.entity.Doctor;
import org.acme.entity.Patient;
import org.acme.repositories.AppointmentRepository;
import org.acme.repositories.DoctorRepository;
import org.acme.repositories.PatientRepository;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AppointmentService {

    @Inject
    AppointmentRepository appointmentRepository;
    @Inject
    DoctorRepository doctorRepository;
    @Inject
    PatientRepository patientRepository;


    @Inject
    ModelMapper modelMapper;

    // Get all appointments
    public List<AppointmentResDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.listAll();
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResDTO.class))
                .collect(Collectors.toList());
    }

    // Get appointment by ID
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id);
        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    // Save a new appointment
    public void saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctorRepository.findById(appointmentDTO.getDid()));
        appointment.setPatient(patientRepository.findById(appointmentDTO.getPid()));
        appointment.setAppointmentDate(LocalDate.now());
        appointment.setSlot(appointmentDTO.getSlot());
        appointmentRepository.persist(appointment);

    }

    // Update an existing appointment
    public void updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentRepository.findById(id);
        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }

        // Update appointment fields
        if (appointmentDTO.getAppointmentDate() != null) {
            appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        }
        if (appointmentDTO.getSlot() != null) {
            appointment.setSlot(appointmentDTO.getSlot());
        }
        if (appointmentDTO.getStatus() != null) {
            appointment.setStatus(appointmentDTO.getStatus());
        }
        // Optionally update patient and doctor references if needed
        if (appointmentDTO.getPid() != 0) {
            appointment.setPatient(modelMapper.map(patientRepository.findById(appointmentDTO.getPid()), Patient.class));
        }
        if (appointmentDTO.getDid() != 0) {
            appointment.setDoctor(modelMapper.map(doctorRepository.findById(appointmentDTO.getDid()), Doctor.class));
        }

        appointmentRepository.persist(appointment);
    }

    // Delete an appointment
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id);
        if (appointment != null) {
            appointmentRepository.delete(appointment);
        } else {
            throw new RuntimeException("Appointment not found");
        }
    }

    // Get appointment count
    public long getAppointmentCount() {
        return appointmentRepository.count();
    }

    public List<String> getSlots(Long did, String date) {
        LocalDate date1=LocalDate.parse(date);
        return appointmentRepository.findAvailableSlots(did,date1);
    }
}
