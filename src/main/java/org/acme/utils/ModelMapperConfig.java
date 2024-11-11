package org.acme.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import org.acme.dto.AppointmentDTO;
import org.acme.dto.AppointmentResDTO;
import org.acme.dto.PatientDTO;
import org.acme.entity.Appointment;
import org.acme.entity.Patient;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@ApplicationScoped
public class ModelMapperConfig {

    @Produces
    @ApplicationScoped
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

//         Configure nested mappings
//        modelMapper.typeMap(Patient.class, PatientDTO.class).addMappings(mapper -> {
//            mapper.map(Patient::getAppointments, PatientDTO::setAppointments);
//        });

        modelMapper.typeMap(Appointment.class, AppointmentResDTO.class).addMappings(mapper -> {
            mapper.map(Appointment::getPatient, AppointmentResDTO::setPatient);
            mapper.map(Appointment::getDoctor, AppointmentResDTO::setDoctor);
        });

        return modelMapper;
    }
}
