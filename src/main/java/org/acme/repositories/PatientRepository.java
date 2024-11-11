package org.acme.repositories;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.PatientDTO;
import org.acme.entity.Doctor;
import org.acme.entity.Patient;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {
    public Patient findByEmail(String email) {
        return find("email", email).firstResult();
    }

}

