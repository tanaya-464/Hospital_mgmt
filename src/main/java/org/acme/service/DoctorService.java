package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.DoctorDTO;
import org.acme.dto.LoginDTO;
import org.acme.entity.Doctor;
import org.acme.repositories.DoctorRepository;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DoctorService {

    @Inject
    DoctorRepository doctorRepository;

    @Inject
    ModelMapper modelMapper;

    // Get a doctor by ID
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    // Get all doctors
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.listAll();
        return doctors.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }

    // Save a new doctor
    public void saveDoctor(Doctor doctor) {
//        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        doctorRepository.persist(doctor);
    }

    // Update an existing doctor
    public void updateDoctor(Long id, DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(id);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }
        // Update the doctor's details
        if (doctorDTO.getName() != null) {
            doctor.setName(doctorDTO.getName());
        }
        if (doctorDTO.getSpecialty() != null) {
            doctor.setSpecialty(doctorDTO.getSpecialty());
        }
        if (doctorDTO.getPhone() != 0) {
            doctor.setPhone(doctorDTO.getPhone());
        }
        if (doctorDTO.getEmail() != null) {
            doctor.setEmail(doctorDTO.getEmail());
        }
        if (doctorDTO.getStart_time() != null) {
            doctor.setStart_time(doctorDTO.getStart_time());
        }
        if (doctorDTO.getEnd_time() != null) {
            doctor.setEnd_time(doctorDTO.getEnd_time());
        }
        if (doctorDTO.getExperience() != 0) {
            doctor.setExperience(doctorDTO.getExperience());
        }
        if (doctorDTO.getLocation() != null) {
            doctor.setLocation(doctorDTO.getLocation());
        }
        if(doctorDTO.getCost() !=0){
            doctor.setCost(doctorDTO.getCost());
        }
        doctorRepository.persist(doctor);
    }

    // Delete a doctor
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id);
        if (doctor != null) {
            doctorRepository.delete(doctor);
        } else {
            throw new RuntimeException("Doctor not found");
        }
    }

    public long getDoctorCount() {
        return doctorRepository.count();
    }

    public boolean validate(LoginDTO loginDTO) {
        Doctor doctor=doctorRepository.findByEmail(loginDTO.getEmail());
        if(doctor!=null
            && loginDTO.getPassword().equals(doctor.getPassword())){
            return true;
        }
        return false;
    }
}
