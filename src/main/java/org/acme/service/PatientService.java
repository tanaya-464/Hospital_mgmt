package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.LoginDTO;
import org.acme.dto.PatientDTO;
import org.acme.entity.Doctor;
import org.acme.entity.Patient;
import org.acme.repositories.PatientRepository;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PatientService {

    @Inject
    PatientRepository patientRepository;

    @Inject
    ModelMapper modelMapper;

    public PatientDTO getPatientById(long id) {
        Patient patient = patientRepository.findById(id);
        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }
        return modelMapper.map(patient, PatientDTO.class);
    }

    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.listAll();
        return patients.stream()
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    public void savePatient(Patient patient) {
        patientRepository.persist(patient);
    }

    public void updatePatient(long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id);
        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }
        if(patientDTO.getId()!=0){
            patient.setId(patientDTO.getId());
        }
        if(patientDTO.getName()!=null){
            patient.setName(patientDTO.getName());
        }
        if(patientDTO.getDob()!=null){
            patient.setDob(patientDTO.getDob());
        }
        if(patientDTO.getGender()!=null){
            patient.setGender(patientDTO.getGender());
        }
        if(patientDTO.getPhone()!=0){
            patient.setPhone(patientDTO.getPhone());
        }
        if(patientDTO.getEmail()!=null){
            patient.setEmail(patientDTO.getEmail());
        }
        if(patientDTO.getPassword()!=null){
            patient.setPassword(patientDTO.getPassword());
        }
        patientRepository.persist(patient);
    }

    public void deletePatient(long id) {
        Patient patient = patientRepository.findById(id);
        if (patient != null) {
            patientRepository.delete(patient);
        }
    }

    public boolean validate(LoginDTO loginDTO) {
        Patient patient=patientRepository.findByEmail(loginDTO.getEmail());
        if(patient!=null
                && loginDTO.getPassword().equals(patient.getPassword())){
            return true;
        }
        return false;
    }
}
